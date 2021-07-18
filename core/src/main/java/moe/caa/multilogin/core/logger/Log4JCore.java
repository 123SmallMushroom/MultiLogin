/*
 * Copyleft (c) 2021 ksqeib,CaaMoe. All rights reserved.
 * @author  ksqeib <ksqeib@dalao.ink> <https://github.com/ksqeib445>
 * @author  CaaMoe <miaolio@qq.com> <https://github.com/CaaMoe>
 * @github  https://github.com/CaaMoe/MultiLogin
 *
 * moe.caa.multilogin.core.logger.Log4JCore
 *
 * Use of this source code is governed by the GPLv3 license that can be found via the following link.
 * https://github.com/CaaMoe/MultiLogin/blob/master/LICENSE
 */

package moe.caa.multilogin.core.logger;

import moe.caa.multilogin.core.main.MultiCore;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;

public class Log4JCore {
    private Logger logger;

    public void init() throws Exception {
        File tempFile = File.createTempFile("log4j2-temp", "multilogin");
        InputStream inputStream = MultiCore.plugin.getJarResource("multiloginLog4j2/log4j2.xml");
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        String config = new String(bytes);
        FileWriter fw = new FileWriter(tempFile);
        fw.write(config.replace("multiloginLog", MultiCore.plugin.getDataFolder().getAbsolutePath()));
        fw.close();
        LoggerContext context = new LoggerContext("MultiLogin");
        context.setConfigLocation(tempFile.toURI());
        context.reconfigure();
        logger = context.getLogger("MultiLogin");
    }

    public void log(LoggerLevel level, String message, Throwable throwable, boolean debug) {
        switch (level) {
            case INFO:
                logger.info(message, throwable);
                break;
            case ERROR:
                logger.log(Level.ERROR, message, throwable);
                break;
            case WARN:
                logger.log(Level.WARN, message, throwable);
                break;
            case DEBUG:
                logger.debug(message, throwable);
                break;
        }
    }
}
