/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anritsu.systemdelta.systemdeltastarter;

import java.io.FileDescriptor;
import java.net.InetSocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.webapp.WebAppContext;
import sun.nio.ch.Secrets;

/**
 *
 * @author ro100051
 */
public class SystemDeltaWebserver {

    private Server server;
    private String warPath;
    String port;

    public SystemDeltaWebserver(String warPath, String port) {
        this.warPath = warPath;
        this.port = port;
        try {
            server = new Server();
            server.setStopAtShutdown(true);
            server.setStopTimeout(5000);
            
            ServerConnector connector = new ServerConnector(server);
            connector.setPort(Integer.parseInt(port));
            
            WebAppContext webapp = new WebAppContext();
            webapp.setContextPath("/");
            webapp.setWar(warPath);
            webapp.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern", ".*/[^/]*jstl.*\\.jar$");
            webapp.setPersistTempDirectory(false);

            //4. Enabling the Annotation based configuration
            org.eclipse.jetty.webapp.Configuration.ClassList classlist = org.eclipse.jetty.webapp.Configuration.ClassList.setServerDefault(server);
            classlist.addAfter("org.eclipse.jetty.webapp.FragmentConfiguration", "org.eclipse.jetty.plus.webapp.EnvConfiguration", "org.eclipse.jetty.plus.webapp.PlusConfiguration");
            classlist.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration", "org.eclipse.jetty.annotations.AnnotationConfiguration");

            server.setHandler(webapp);
            
            // Configure https
//            HttpConfiguration https = new HttpConfiguration();
//            https.addCustomizer(new SecureRequestCustomizer());
//            
//            SslContextFactory sslContextFactory = new SslContextFactory();
//            sslContextFactory.setKeyStorePath(getClass().getClassLoader().getResource("repositorygenerator.jks").toExternalForm());
//            sslContextFactory.setKeyStorePassword("repositorygenerator");
//            sslContextFactory.setKeyManagerPassword("repositorygenerator");
//            
//            ServerConnector sslConnector = new ServerConnector(server, new SslConnectionFactory(sslContextFactory, "http/1.1"), new HttpConnectionFactory(https));
//            sslConnector.setPort(Integer.parseInt(port));
                server.setConnectors(new Connector[]{connector});
            
            
            server.start();
        } catch (Exception ex) {
            Logger.getLogger(SystemDeltaWebserver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
