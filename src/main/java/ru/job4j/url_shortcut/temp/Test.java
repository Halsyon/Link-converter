package ru.job4j.url_shortcut.temp;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class Test {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String address = "https://www.google.ru/search?q=java.net.urldecoder.decode&newwindow=1&source=hp&ei=t8gHYpHwFJWGxc8P2sejkAg&iflsig=AHkkrS4AAAAAYgfWx74VkchuI2ZCB8Baz3c6LZ8sEta7&oq=java.net.URLDe&gs_lcp=Cgdnd3Mtd2l6EAMYADIFCAAQgAQyBQgAEIAEMgQIABAeMgQIABAeMgQIABAeMgQIABAeMgQIABAeMgYIABAKEB4yBAgAEB4yBAgAEB46CwguEIAEEMcBENEDOggILhCABBDUAjoLCC4QgAQQxwEQowI6DgguEIAEEMcBENEDENQCOgUILhCABDoKCC4QxwEQowIQCjoECAAQCjoLCC4QgAQQxwEQrwFQxQlYm6sBYI3SAWgFcAB4AIABrAGIAYYVkgEEMC4xOJgBAKABAbABAA&sclient=gws-wiz";
        String uncode = URLEncoder.encode(address, "UTF-8");
        System.out.println("AFTER UNCODE " + uncode);

//        String decode = URLDecoder.decode(decode, "UTF-8");
//        System.out.println("AFTER " + decode);
    }
}
