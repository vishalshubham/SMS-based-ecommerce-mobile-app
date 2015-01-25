/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mba;

import javax.microedition.io.Connector;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;

/**
 *
 * @author VISHAL
 */
public class SendSMS
{
    public static boolean sendSms(String message)
    {
		boolean result = true;
		try {
			//sets address to send message
			String addr = "sms://+919890122862";
			// opens connection
			MessageConnection conn = (MessageConnection) Connector.open(addr);
			// prepares text message
			TextMessage msg =
				(TextMessage)conn.newMessage(MessageConnection.TEXT_MESSAGE);
			//set text
			msg.setPayloadText(message);
			// send message
			conn.send(msg);
			conn.close();
		}
		catch(SecurityException se)
		{
			result = false;
		}
		catch (Exception e)
		{
			result = false;
		}
		return result;
	}
}
