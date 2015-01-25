/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hello;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;

/**
 *
 * @author VISHAL
 */
public class FormResult implements CommandListener
{
    Form form, mainform;
    Command back;
    
    StringItem str = new StringItem("Thanx for visiting !", "Request Sent");

    static Form getFormResult()
    {
        FormResult result = new FormResult();
        return result.makeFormResult();
    }

    private Form makeFormResult()
    {
        form = new Form("Successfully Request sent");
        form.append(str);
        back = new Command("Back", Command.BACK, 1);
        form.addCommand(back);
        form.setCommandListener(this);
        return form;
    }

    public void commandAction(Command c, Displayable d)
    {
       if(d == form)
       {
           if(c == back)
           {
               mainform = FormSend.getFormSend();
               MobileMidlet.switchDisplay(mainform);
           }
       }
    }

}
