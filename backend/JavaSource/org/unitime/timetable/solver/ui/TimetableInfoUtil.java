/*
 * Licensed to The Apereo Foundation under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for
 * additional information regarding copyright ownership.
 *
 * The Apereo Foundation licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.unitime.timetable.solver.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.unitime.timetable.ApplicationProperties;
import org.unitime.timetable.solver.jgroups.CourseSolverContainer;
import org.unitime.timetable.solver.jgroups.SolverServer;
import org.unitime.timetable.solver.jgroups.SolverServerImplementation;


/**
 * @author Tomas Muller
 */
public class TimetableInfoUtil implements TimetableInfoFileProxy {
	private static Log sLog = LogFactory.getLog(TimetableInfoUtil.class);
	private static TimetableInfoUtil sInstance = new TimetableInfoUtil();

	private TimetableInfoUtil() {}

	public static TimetableInfoUtil getLocalInstance() { return sInstance; }

	public static TimetableInfoFileProxy getInstance() {
		// Create the cluster instance
		SolverServer server = SolverServerImplementation.getInstance();
		if (server != null && server.getCourseSolverContainer() != null)
			return ((CourseSolverContainer)server.getCourseSolverContainer()).getFileProxy();

		// Fall back to local instance
		return getLocalInstance();
	}

	@Override
	public boolean saveToFile(String name, TimetableInfo info) {
		try {
			File file = new File(ApplicationProperties.getBlobFolder(), name);
			file.getParentFile().mkdirs();

			// Only the Streams go in the try-with-resources because they implement Closeable
			try (FileOutputStream out = new FileOutputStream(file);
				 GZIPOutputStream gzipOut = new GZIPOutputStream(out)) {

				// Initialize the writer inside the block
				XMLWriter writer = new XMLWriter(gzipOut, OutputFormat.createCompactFormat());

				Document document = DocumentHelper.createDocument();
				Element root = document.addElement(info.getClass().getName());
				info.save(root);

				writer.write(document);
				writer.flush();

				// We close the writer here. Because gzipOut is in the try-block,
				// it will still close automatically even if an error happens above.
				writer.close();
			}

			sLog.info("Saved info " + name + " as " + file + " (" + file.length() + " bytes)");
			return true;
		} catch (Exception e) {
			sLog.warn("Failed to save info " + name + ": " + e.getMessage(), e);
			return false;
		}
	}
	@Override
	public TimetableInfo loadFromFile(String name) {
		try {
			File file = new File(ApplicationProperties.getBlobFolder(), name);
			if (!file.exists()) return null;

			sLog.info("Loading info " + name + " from " + file + " (" + file.length() + " bytes)");

			Document document = null;
			// Try-with-resources ensures streams are closed even if SAXReader fails
			try (FileInputStream fis = new FileInputStream(file);
				 GZIPInputStream gzipInput = new GZIPInputStream(fis)) {
				document = (new SAXReader()).read(gzipInput);
			}

			if (document == null) return null;

			Element root = document.getRootElement();
			String infoClassName = root.getName();
			Class<?> infoClass = Class.forName(infoClassName);
			TimetableInfo info = (TimetableInfo) infoClass.getConstructor().newInstance();
			info.load(root);

			return info;
		} catch (Exception e) {
			sLog.warn("Failed to load info " + name + ": " + e.getMessage(), e);
			return null;
		}
	}

	@Override
	public boolean deleteFile(String name) {
		try {
			File file = new File(ApplicationProperties.getBlobFolder(), name);
			if (file.exists()) {
				sLog.info("Deleting info " + name + " as " + file + " (" + file.length() + " bytes)");
				return file.delete();
			}
			return false;
		} catch (Exception e) {
			sLog.warn("Failed to delete info " + name + ": " + e.getMessage(), e);
			return false;
		}
	}
}