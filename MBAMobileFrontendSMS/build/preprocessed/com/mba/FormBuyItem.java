

package com.mba;

import javax.microedition.lcdui.*;
import java.util.Hashtable;
/**
 *
 * @author VISHAL
 */

//130.Snacky 110gm=@10,131.Sunfeast glucose 53gm=@4,


public class FormBuyItem implements CommandListener
{
    private TextField tquantity;
    private ChoiceGroup itemlist;
    private String category;
    private StringItem choose;
    static String bill="";
    private char cbill[];

    private String item="";
    private char citem[]=item.toCharArray();
    private Command ok6;
    private Command ok11;
    private Command back6;
    private List formmenu;
    private Form formcheckyourcart;

    private String Soaps = "143.Medimix 120gm=@22,144.Santoor 90gm=@15,145.Santoor 400gm=@62,146.Liril 125gm=@35,147.Pears 125gm=@42,148.Dove 300gm=@135,149.Dove 75gm=@36,150.Lux 270gm=@49,151.Lux 150gm=@30,";
    private String Detergent = "179.Surf Excel 245gm=@17,180.Surf Excel 140gm=@10,181.Surf Excel 3.8kg=@430,182.Surf Excel 2kg=@405,183.Surf Excel 1kg=@199,184.Surf Excel 500gm=@100,185.Ariel 3kg=@398,186.Ariel 2kg=@315,187.Ariel 1.2kg=@199,188.Ariel 1kg=@167,189.Ariel 500gm=@100,190.Henko 5kg=@525,191.Henko 1kg=@180,192.Henko 500gm=@90,193.Rin 6kg=@285,194.Rin 1kg=@58,195.Rin 500gm=@29,196.Rin Matic 1kg=@85,197.Tide 6kg=@390,198.Tide 2kg=@140,199.Tide 1kg=@100,200.White Magic 750gm=@60,201.Wheel 4kg=@160,202.Wheel 630gm=@20,203.Mr. White 1kg=@54,";
    private String Juice = "63.Tropicana Juice 1lit=@75,64.Tropicana Juice 500ml=@40,65.Real Juice 1lit=@85,66.Real Juice 500ml=@45,";
    private String Biscuit = "130.Snacky 110gm=@10,131.Sunfeast glucose 53gm=@4,132.Sunfeast glucose 66gm=@5,133.Sunfeast glucose 150gm=@10,134.Sunfeast glucose 312gm=@20,135.Hide & Seek 94gm=@18,136.Hide & Seek 144gm=@40,137.Parle Monaco 120gm=@10,138.Parle Monaco 240gm=@20,139.Marie 135gm=@10,140.Marie 280gm=@20,141.Marie Gold 140gm=@15,142.Aliva 150gm=@12,";
    private String Chips="18.Lays 28gm=@10,19.Lays 63gm=@20,20.Kurkure 52gm=@10,21.Kurkure 130gm=@20,22.Bingo 39gm=@10,23.Bingo 66gm=@20,24.Bingo 120gm=@25,25.Parles 36gm=@10,26.Picnic 70gm=@21,";
    private String Deodrant="204.Gillette 195ml=@199,205.Gillette 150ml=@149,206.DJ&C 100gm=@109,207.DJ&C 150gm=@125,208.Axe 150ml=@135,209.Axe 180ml=@150,210.Octane 150ml=@150,211.Old Spice 150ml=@175,212.Fa 150ml=@140,213.Yardley 150ml=@150,214.Setwet 150ml=@120,";
    private String Fruits ="215.Apples=@60,216.Bananas=@30,217.Cherry=@50,218.Guava=@60,219.Grapes=@80,220.Muskmelons=@25,221.Oranges=@40,222.Peach=@60,223.Pears=@50,224.Strawberry=@115,225.Watermelon=@20,";
    

    private Hashtable list;
    


    public Form makeFormBuyItem(String category)
    {
        list = new Hashtable();
        list.put("Soaps", Soaps);
        list.put("Detergent", Detergent);
        list.put("Juice", Juice);
        list.put("Biscuit",Biscuit);
        list.put("Chips",Chips);
        list.put("Deodrant",Deodrant);
        list.put("Fruits",Fruits);
        
        Form fbuyitem=new Form("CATEGORY");
        tquantity=new TextField("QUANTITY :",null,3,TextField.NUMERIC);
        itemlist=new ChoiceGroup("List of "+category,ChoiceGroup.POPUP);
        choose=new StringItem(null, null);
        item = (String) list.get(category);
//        item=returnItem(category);
        citem=item.toCharArray();
        returnList(itemlist,citem);
        fbuyitem.append(itemlist);
        fbuyitem.append(choose);
        fbuyitem.append(tquantity);
        ok6=new Command("Add To Cart",Command.OK,1);
        ok11=new Command("View your Cart",Command.OK,1);
        back6=new Command("Back",Command.BACK,1);
        fbuyitem.addCommand(ok6);
        fbuyitem.addCommand(ok11);
        fbuyitem.addCommand(back6);
        fbuyitem.setCommandListener(this);

        return fbuyitem;
    }

    static Form getFormBuyItem(String category)
    {
        FormBuyItem fb = new FormBuyItem();
        return fb.makeFormBuyItem(category);
    }

    public void returnList(ChoiceGroup c,char citem[])
    {
        String name="";
        int i;
        for(i=0;i<citem.length;i++)
        {

                    if(citem[i]!=',')
                    {
                        name=name+citem[i];
                    }
                    else
                    {
                        c.append(name, null);
                        System.out.println(name);
                        name="";
                    }
        }
    }

    private void showCart()
    {
        if(!(FormBuyItem.bill.equals("")))
        {
            System.out.println(FormBuyItem.bill);
            formcheckyourcart=FormCheckYourCart.getFormCheckYourCart();
            MobileMidlet.switchDisplay(formcheckyourcart);
            System.out.println("Choosed : Check Your Cart");
        }
        else
        {
            Alert alert= new Alert("SORRY ::","No Purchases yet! Please purchase Something",null,null);
            alert.setTimeout(Alert.FOREVER);
            alert.setType(AlertType.ERROR);
            MobileMidlet.switchDisplay(alert);
        }
    }

    public void commandAction(Command c, Displayable d)
    {
        if(c==ok6)
            {
                if(!(tquantity.getString().equals("")))
                {
                    bill=bill+tquantity.getString()+"-"+itemlist.getString(itemlist.getSelectedIndex())+",";
                    choose.setLabel("Added To Your Cart : "+tquantity.getString()+"--"+itemlist.getString(itemlist.getSelectedIndex()));
                    System.out.println("Your bill : "+bill);
                }
                else
                {
                    Alert alert= new Alert("QUANTITY CANNOT BE BLANK::","Please Enter any Quantity for Purchase",null,null);
                    alert.setTimeout(Alert.FOREVER);
                    alert.setType(AlertType.INFO);
                    MobileMidlet.switchDisplay(alert);
                }
            }
            else if(c==ok11)
            {
                showCart();
            }
            else if(c==back6)
            {
                formmenu = FormMenu.getFormMenu();
                MobileMidlet.switchDisplay(formmenu);
            }
    }

}
