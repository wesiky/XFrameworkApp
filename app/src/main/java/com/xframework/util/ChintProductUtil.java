package com.xframework.util;

import com.xframework.model.BaseProductBody;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChintProductUtil {
    public static BaseProductBody checkProduct(String productDescription) {
        try {
            productDescription = productDescription.toUpperCase().replaceAll("[\\u4e00-\\u9fa5]", "").replaceAll("\\s{2,}", " ");
            BaseProductBody baseProductBody = new BaseProductBody();
            for (String field : productDescription.split(" ")) {
                if (field.matches("^\\d+A")) {
                    baseProductBody.setRatedCurrent(field);
                } else if (field.matches("^[0-9|.]*MA")) {
                    baseProductBody.setResidualCurrent(field);
                } else if (field.matches("^\\d+\\.\\d*S")) {
                    baseProductBody.setInterruptionTime(field);
                } else {
                    setBaseData(baseProductBody, field);
                }
            }
            return baseProductBody;
        } catch (Exception exception) {
            return null;
        }
    }

    public static boolean isDeliveryBody(BaseProductBody baseProduct, BaseProductBody deliveryProduct) {
        if (baseProduct.getSeries().equals(deliveryProduct.getSeries()) && baseProduct.getFrame().equals(deliveryProduct.getFrame()) && baseProduct.getPoles().equals(deliveryProduct.getPoles()) && baseProduct.getProtect().equals(deliveryProduct.getProtect()) && baseProduct.getLevel().equals(deliveryProduct.getLevel()) && baseProduct.getRatedCurrent().equals(deliveryProduct.getRatedCurrent())) {
            if (baseProduct.getInterruptionTime().equals(deliveryProduct.getInterruptionTime()))
                return true;
            if (baseProduct.getInterruptionTime().equals("0.2S")) {
                boolean bool = deliveryProduct.getInterruptionTime().equals("");
                if (bool)
                    return true;
            }
            return false;
        }
        return false;
    }

    private static void setBaseData(BaseProductBody productBody, String field) {
        if (field.matches("^[A-Z0-9]{3,}-[A-Z0-9]{2,}/[A-Z0-9]{3,}")) {
            String front = "";
            String middle = "";
            String later = "";
            Matcher matcher = Pattern.compile("(.+)-").matcher(field);
            if (matcher.find())
                front = matcher.group().replaceAll("-", "");
            matcher = Pattern.compile("-(.+)/").matcher(field);
            if (matcher.find()) {
                middle = matcher.group().replaceAll("-|/", "");
                middle = middle.replaceAll("[Z|P]$", "");
            }
            matcher = Pattern.compile("/(.+)").matcher(field);
            if (matcher.find())
                later = matcher.group().replaceAll("/", "");

            if (front.matches("^DZ20L")) {
                productBody.setSeries("DZ20L");
                productBody.setLevel("");
                productBody.setFrame(middle);
                matcher = Pattern.compile("^[2-4]N?").matcher(later);
                if (matcher.find()) {
                    productBody.setPoles(matcher.group());
                } else {
                    productBody.setPoles("");
                }
                later = later.substring(productBody.getPoles().length());
                productBody.setTripType(later.substring(0, 1));
                later = later.substring(productBody.getTripType().length());
                matcher = Pattern.compile("^[0-9]{2}Y?").matcher(later);
                if (matcher.find()) {
                    productBody.setAccessories(matcher.group());
                } else {
                    productBody.setAccessories("");
                }
                later = later.substring(productBody.getAccessories().length());
                matcher = Pattern.compile("^[0|2]").matcher(later);
                if (matcher.find()) {
                    productBody.setPurpose(matcher.group());
                } else {
                    productBody.setPurpose("");
                }
                later = later.substring(productBody.getPurpose().length());
                matcher = Pattern.compile("[A|B|C|D]").matcher(later);
                if (matcher.find()) {
                    productBody.setProtect(matcher.group());
                }
                else {
                    productBody.setProtect("");
                }
            }
            else if (front.matches("^DZ20")) {
                productBody.setSeries("DZ20");
                productBody.setLevel(front.substring(front.length() - 1));
                productBody.setFrame(middle);
                matcher = Pattern.compile("^[2-4]N?").matcher(later);
                if (matcher.find()) {
                    productBody.setPoles(matcher.group());
                } else {
                    productBody.setPoles("");
                }
                productBody.setTripType(later.substring(0, 1));
                later = later.substring(productBody.getTripType().length());
                matcher = Pattern.compile("^[0-9]{2}Y?").matcher(later);
                if (matcher.find()) {
                    productBody.setAccessories(matcher.group());
                } else {
                    productBody.setAccessories("");
                }
                later = later.substring(productBody.getAccessories().length());
                matcher = Pattern.compile("^[0|2]").matcher(later);
                if (matcher.find()) {
                    productBody.setPurpose(matcher.group());
                }
                else {
                    productBody.setPurpose("");
                }
                later = later.substring(productBody.getPurpose().length());
                matcher = Pattern.compile("[A|B|C|D]").matcher(later);
                if (matcher.find()) {
                    productBody.setProtect(matcher.group());
                }
                else {
                    productBody.setProtect("");
                }
            }
            else if (front.matches("^NM10")) {
                productBody.setSeries("NM10");
                matcher = Pattern.compile("[S|H|R]").matcher(middle);
                if (matcher.find()) {
                    productBody.setLevel(matcher.group());
                } else {
                    productBody.setLevel("");
                }
                middle = middle.substring(0, middle.length() - productBody.getLevel().length());
                matcher = Pattern.compile("^(.*)").matcher(middle);
                if (matcher.find()) {
                    productBody.setFrame(matcher.group());
                } else {
                    productBody.setFrame("");
                }
                matcher = Pattern.compile("^[2-4]N?").matcher(later);
                if (matcher.find()) {
                    productBody.setPoles(matcher.group());
                } else {
                    productBody.setPoles("");
                }
                later = later.substring(productBody.getPoles().length());
                productBody.setTripType(later.substring(0, 1));
                later = later.substring(productBody.getTripType().length());
                matcher = Pattern.compile("^[0-9]").matcher(later);
                if (matcher.find()) {
                    productBody.setAccessories(matcher.group());
                } else {
                    productBody.setAccessories("");
                }
                later = later.substring(productBody.getAccessories().length());
                matcher = Pattern.compile("^[0|2]").matcher(later);
                if (matcher.find()) {
                    productBody.setPurpose(matcher.group());
                } else {
                    productBody.setPurpose("");
                }
                later = later.substring(productBody.getPurpose().length());
                matcher = Pattern.compile("[A|B|C|D]").matcher(later);
                if (matcher.find()) {
                    productBody.setProtect(matcher.group());
                }
                else {
                    productBody.setProtect("");
                }
            }
            else if (front.matches("^NM1B")) {
                productBody.setSeries("NM1B");
                matcher = Pattern.compile("[S|H|R]").matcher(middle);
                if (matcher.find()) {
                    productBody.setLevel(matcher.group());
                } else {
                    productBody.setLevel("");
                }
                middle = middle.substring(0, middle.length() - productBody.getLevel().length());
                matcher = Pattern.compile("^(.*)").matcher(middle);
                if (matcher.find()) {
                    productBody.setFrame(matcher.group());
                } else {
                    productBody.setFrame("");
                }
                matcher = Pattern.compile("^[2-4]N?").matcher(later);
                if (matcher.find()) {
                    productBody.setPoles(matcher.group());
                } else {
                    productBody.setPoles("");
                }
                later = later.substring(productBody.getPoles().length());
                productBody.setTripType(later.substring(0, 1));
                later = later.substring(productBody.getTripType().length());
                matcher = Pattern.compile("^[0-9]Y?").matcher(later);
                if (matcher.find()) {
                    productBody.setAccessories(matcher.group());
                } else {
                    productBody.setAccessories("");
                }
                later = later.substring(productBody.getAccessories().length());
                matcher = Pattern.compile("^[0|2]").matcher(later);
                if (matcher.find()) {
                    productBody.setPurpose(matcher.group());
                } else {
                    productBody.setPurpose("");
                }
                later = later.substring(productBody.getPurpose().length());
                matcher = Pattern.compile("[A|B|C|D]").matcher(later);
                if (matcher.find()) {
                    productBody.setProtect(matcher.group());
                }
                else {
                    productBody.setProtect("");
                }
            }
            else if (front.matches("^NM1LE")) {
                productBody.setSeries("NM1LE");
                matcher = Pattern.compile("[S|H|R]").matcher(middle);
                if (matcher.find()) {
                    productBody.setLevel(matcher.group());
                } else {
                    productBody.setLevel("");
                }
                middle = middle.substring(0, middle.length() - productBody.getLevel().length());
                matcher = Pattern.compile("^(.*)").matcher(middle);
                if (matcher.find()) {
                    productBody.setFrame(matcher.group());
                } else {
                    productBody.setFrame("");
                }
                matcher = Pattern.compile("^[2-4]N?").matcher(later);
                if (matcher.find()) {
                    productBody.setPoles(matcher.group());
                } else {
                    productBody.setPoles("");
                }
                later = later.substring(productBody.getPoles().length());
                productBody.setTripType(later.substring(0, 1));
                later = later.substring(productBody.getTripType().length());
                matcher = Pattern.compile("^[0-9]Y?").matcher(later);
                if (matcher.find()) {
                    productBody.setAccessories(matcher.group());
                } else {
                    productBody.setAccessories("");
                }
                later = later.substring(productBody.getAccessories().length());
                matcher = Pattern.compile("^[0|2]").matcher(later);
                if (matcher.find()) {
                    productBody.setPurpose(matcher.group());
                } else {
                    productBody.setPurpose("");
                }
                later = later.substring(productBody.getPurpose().length());
                matcher = Pattern.compile("[A|B|C|D]").matcher(later);
                if (matcher.find()) {
                    productBody.setProtect(matcher.group());
                }
                else {
                    productBody.setProtect("");
                }
            }
            else if (front.matches("^NM1")) {
                productBody.setSeries("NM1");
                matcher = Pattern.compile("[S|H|R]").matcher(middle);
                if (matcher.find()) {
                    productBody.setLevel(matcher.group());
                } else {
                    productBody.setLevel("");
                }
                middle = middle.substring(0, middle.length() - productBody.getLevel().length());
                matcher = Pattern.compile("^(.*)").matcher(middle);
                if (matcher.find()) {
                    productBody.setFrame(matcher.group());
                } else {
                    productBody.setFrame("");
                }
                matcher = Pattern.compile("^[2-4]N?").matcher(later);
                if (matcher.find()) {
                    productBody.setPoles(matcher.group());
                } else {
                    productBody.setPoles("");
                }
                later = later.substring(productBody.getPoles().length());
                productBody.setTripType(later.substring(0, 1));
                later = later.substring(productBody.getTripType().length());
                matcher = Pattern.compile("^[0-9]{2}Y?").matcher(later);
                if (matcher.find()) {
                    productBody.setAccessories(matcher.group());
                } else {
                    productBody.setAccessories("");
                }
                later = later.substring(productBody.getAccessories().length());
                matcher = Pattern.compile("^[0|2]").matcher(later);
                if (matcher.find()) {
                    productBody.setPurpose(matcher.group());
                } else {
                    productBody.setPurpose("");
                }
                later = later.substring(productBody.getPurpose().length());
                matcher = Pattern.compile("[A|B|C|D]").matcher(later);
                if (matcher.find()) {
                    productBody.setProtect(matcher.group());
                }
                else {
                    productBody.setProtect("");
                }
            }
            else if (front.matches("^NXMSH")) {
                productBody.setSeries("NXMSH");
                matcher = Pattern.compile("[S|H|R]").matcher(middle);
                if (matcher.find()) {
                    productBody.setLevel(matcher.group());
                } else {
                    productBody.setLevel("");
                }
                middle = middle.substring(0, middle.length() - productBody.getLevel().length());
                matcher = Pattern.compile("^(.*)").matcher(middle);
                if (matcher.find()) {
                    productBody.setFrame(matcher.group());
                } else {
                    productBody.setFrame("");
                }
                matcher = Pattern.compile("^[2-4]N?").matcher(later);
                if (matcher.find()) {
                    productBody.setPoles(matcher.group());
                } else {
                    productBody.setPoles("");
                }
                later = later.substring(productBody.getPoles().length());
                productBody.setTripType(later.substring(0, 1));
                later = later.substring(productBody.getTripType().length());
                matcher = Pattern.compile("^[0-9]{2}Y?").matcher(later);
                if (matcher.find()) {
                    productBody.setAccessories(matcher.group());
                } else {
                    productBody.setAccessories("");
                }
                later = later.substring(productBody.getAccessories().length());
                matcher = Pattern.compile("^[0|2]").matcher(later);
                if (matcher.find()) {
                    productBody.setPurpose(matcher.group());
                } else {
                    productBody.setPurpose("");
                }
                later = later.substring(productBody.getPurpose().length());
                matcher = Pattern.compile("[A|B|C|D]").matcher(later);
                if (matcher.find()) {
                    productBody.setProtect(matcher.group());
                }
                else {
                    productBody.setProtect("");
                }
            }
            else if (front.matches("^NXMSP")) {
                productBody.setSeries("NXMSP");
                matcher = Pattern.compile("[S|H|R]").matcher(middle);
                if (matcher.find()) {
                    productBody.setLevel(matcher.group());
                } else {
                    productBody.setLevel("");
                }
                middle = middle.substring(0, middle.length() - productBody.getLevel().length());
                matcher = Pattern.compile("^(.*)").matcher(middle);
                if (matcher.find()) {
                    productBody.setFrame(matcher.group());
                } else {
                    productBody.setFrame("");
                }
                matcher = Pattern.compile("^[2-4]N?").matcher(later);
                if (matcher.find()) {
                    productBody.setPoles(matcher.group());
                } else {
                    productBody.setPoles("");
                }
                later = later.substring(productBody.getPoles().length());
                productBody.setTripType(later.substring(0, 1));
                later = later.substring(productBody.getTripType().length());
                matcher = Pattern.compile("^[0-9]{2}Y?").matcher(later);
                if (matcher.find()) {
                    productBody.setAccessories(matcher.group());
                } else {
                    productBody.setAccessories("");
                }
                later = later.substring(productBody.getAccessories().length());
                matcher = Pattern.compile("^[0|2]").matcher(later);
                if (matcher.find()) {
                    productBody.setPurpose(matcher.group());
                } else {
                    productBody.setPurpose("");
                }
                later = later.substring(productBody.getPurpose().length());
                matcher = Pattern.compile("[A|B|C|D]").matcher(later);
                if (matcher.find()) {
                    productBody.setProtect(matcher.group());
                }
                else {
                    productBody.setProtect("");
                }
            }
            else if (front.matches("^NXMS")) {
                productBody.setSeries("NXMS");
                matcher = Pattern.compile("[S|H|R]").matcher(middle);
                if (matcher.find()) {
                    productBody.setLevel(matcher.group());
                } else {
                    productBody.setLevel("");
                }
                middle = middle.substring(0, middle.length() - productBody.getLevel().length());
                matcher = Pattern.compile("^(.*)").matcher(middle);
                if (matcher.find()) {
                    productBody.setFrame(matcher.group());
                } else {
                    productBody.setFrame("");
                }
                matcher = Pattern.compile("^[2-4]N?").matcher(later);
                if (matcher.find()) {
                    productBody.setPoles(matcher.group());
                } else {
                    productBody.setPoles("");
                }
                later = later.substring(productBody.getPoles().length());
                productBody.setTripType(later.substring(0, 1));
                later = later.substring(productBody.getTripType().length());
                matcher = Pattern.compile("^[0-9]{2}Y?").matcher(later);
                if (matcher.find()) {
                    productBody.setAccessories(matcher.group());
                } else {
                    productBody.setAccessories("");
                }
                later = later.substring(productBody.getAccessories().length());
                matcher = Pattern.compile("^[0|2]").matcher(later);
                if (matcher.find()) {
                    productBody.setPurpose(matcher.group());
                } else {
                    productBody.setPurpose("");
                }
                later = later.substring(productBody.getPurpose().length());
                matcher = Pattern.compile("[A|B|C|D]").matcher(later);
                if (matcher.find()) {
                    productBody.setProtect(matcher.group());
                }
                else {
                    productBody.setProtect("");
                }
            }
            else if (front.matches("^NXMLE")) {
                productBody.setSeries("NXMLE");
                matcher = Pattern.compile("[S|H|R]").matcher(middle);
                if (matcher.find()) {
                    productBody.setLevel(matcher.group());
                } else {
                    productBody.setLevel("");
                }
                middle = middle.substring(0, middle.length() - productBody.getLevel().length());
                matcher = Pattern.compile("^(.*)").matcher(middle);
                if (matcher.find()) {
                    productBody.setFrame(matcher.group());
                } else {
                    productBody.setFrame("");
                }
                matcher = Pattern.compile("^[2-4]N?").matcher(later);
                if (matcher.find()) {
                    productBody.setPoles(matcher.group());
                } else {
                    productBody.setPoles("");
                }
                later = later.substring(productBody.getPoles().length());
                productBody.setTripType(later.substring(0, 1));
                later = later.substring(productBody.getTripType().length());
                matcher = Pattern.compile("^[0-9]{2}Y?").matcher(later);
                if (matcher.find()) {
                    productBody.setAccessories(matcher.group());
                } else {
                    productBody.setAccessories("");
                }
                later = later.substring(productBody.getAccessories().length());
                matcher = Pattern.compile("^[0|2]").matcher(later);
                if (matcher.find()) {
                    productBody.setPurpose(matcher.group());
                } else {
                    productBody.setPurpose("");
                }
                later = later.substring(productBody.getPurpose().length());
                matcher = Pattern.compile("[A|B|C|D]").matcher(later);
                if (matcher.find()) {
                    productBody.setProtect(matcher.group());
                }
                else {
                    productBody.setProtect("");
                }
            }
            else if (front.matches("^NXMH")) {
                if (later.matches("(.*)G(.*)"))
                    productBody.setSeries("NXMH-G");
                if (later.matches("(.*)T(.*)")) {
                    productBody.setSeries("NXMH-T");
                } else {
                    productBody.setSeries("NXMH");
                }
                later = later.replaceAll("[G|T]", "");
                matcher = Pattern.compile("[S|H|R]").matcher(middle);
                if (matcher.find()) {
                    productBody.setLevel(matcher.group());
                } else {
                    productBody.setLevel("");
                }
                middle = middle.substring(0, middle.length() - productBody.getLevel().length());
                matcher = Pattern.compile("^(.*)").matcher(middle);
                if (matcher.find()) {
                    productBody.setFrame(matcher.group());
                } else {
                    productBody.setFrame("");
                }
                matcher = Pattern.compile("^[2-4]N?").matcher(later);
                if (matcher.find()) {
                    productBody.setPoles(matcher.group());
                } else {
                    productBody.setPoles("");
                }
                later = later.substring(productBody.getPoles().length());
                productBody.setTripType(later.substring(0, 1));
                later = later.substring(productBody.getTripType().length());
                matcher = Pattern.compile("^[0-9]{2}Y?").matcher(later);
                if (matcher.find()) {
                    productBody.setAccessories(matcher.group());
                } else {
                    productBody.setAccessories("");
                }
                later = later.substring(productBody.getAccessories().length());
                matcher = Pattern.compile("^[0|2]").matcher(later);
                if (matcher.find()) {
                    productBody.setPurpose(matcher.group());
                } else {
                    productBody.setPurpose("");
                }
                later = later.substring(productBody.getPurpose().length());
                matcher = Pattern.compile("[A|B|C|D]").matcher(later);
                if (matcher.find()) {
                    productBody.setProtect(matcher.group());
                }
                else {
                    productBody.setProtect("");
                }
            }
            else if (front.matches("^NXMP")) {
                if (later.matches("(.*)G(.*)"))
                    productBody.setSeries("NXMP-G");
                if (later.matches("(.*)T(.*)")) {
                    productBody.setSeries("NXMP-T");
                } else {
                    productBody.setSeries("NXMP");
                }
                later = later.replaceAll("[G|T]", "");
                matcher = Pattern.compile("[S|H|R]").matcher(middle);
                if (matcher.find()) {
                    productBody.setLevel(matcher.group());
                } else {
                    productBody.setLevel("");
                }
                middle = middle.substring(0, middle.length() - productBody.getLevel().length());
                matcher = Pattern.compile("^(.*)").matcher(middle);
                if (matcher.find()) {
                    productBody.setFrame(matcher.group());
                } else {
                    productBody.setFrame("");
                }
                matcher = Pattern.compile("^[2-4]N?").matcher(later);
                if (matcher.find()) {
                    productBody.setPoles(matcher.group());
                } else {
                    productBody.setPoles("");
                }
                later = later.substring(productBody.getPoles().length());
                productBody.setTripType(later.substring(0, 1));
                later = later.substring(productBody.getTripType().length());
                matcher = Pattern.compile("^[0-9]{2}Y?").matcher(later);
                if (matcher.find()) {
                    productBody.setAccessories(matcher.group());
                } else {
                    productBody.setAccessories("");
                }
                later = later.substring(productBody.getAccessories().length());
                matcher = Pattern.compile("^[0|2]").matcher(later);
                if (matcher.find()) {
                    productBody.setPurpose(matcher.group());
                } else {
                    productBody.setPurpose("");
                }
                later = later.substring(productBody.getPurpose().length());
                matcher = Pattern.compile("[A|B|C|D]").matcher(later);
                if (matcher.find()) {
                    productBody.setProtect(matcher.group());
                }
                else {
                    productBody.setProtect("");
                }
            }
            else if (front.matches("^NXM")) {
                if (later.matches("(.*)G(.*)"))
                    productBody.setSeries("NXM-G");
                if (later.matches("(.*)T(.*)")) {
                    productBody.setSeries("NXM-T");
                } else {
                    productBody.setSeries("NXM");
                }
                later = later.replaceAll("[G|T]", "");
                matcher = Pattern.compile("[S|H|R]").matcher(middle);
                if (matcher.find()) {
                    productBody.setLevel(matcher.group());
                } else {
                    productBody.setLevel("");
                }
                middle = middle.substring(0, middle.length() - productBody.getLevel().length());
                Matcher matcher5 = Pattern.compile("^(.*)").matcher(middle);
                if (matcher5.find()) {
                    productBody.setFrame(matcher5.group());
                } else {
                    productBody.setFrame("");
                }
                matcher5 = Pattern.compile("^[2-4]N?").matcher(later);
                if (matcher5.find()) {
                    productBody.setPoles(matcher5.group());
                } else {
                    productBody.setPoles("");
                }
                later = later.substring(productBody.getPoles().length());
                productBody.setTripType(later.substring(0, 1));
                later = later.substring(productBody.getTripType().length());
                matcher5 = Pattern.compile("^[0-9]{2}Y?").matcher(later);
                if (matcher5.find()) {
                    productBody.setAccessories(matcher5.group());
                } else {
                    productBody.setAccessories("");
                }
                later = later.substring(productBody.getAccessories().length());
                matcher5 = Pattern.compile("^[0|2]").matcher(later);
                if (matcher5.find()) {
                    productBody.setPurpose(matcher5.group());
                } else {
                    productBody.setPurpose("");
                }
                later = later.substring(productBody.getPurpose().length());
                Matcher matcher4 = Pattern.compile("[A|B|C|D]").matcher(later);
                if (matcher4.find()) {
                    productBody.setProtect(matcher4.group());
                }
                else {
                    productBody.setProtect("");
                }
            }
        }
    }
}
