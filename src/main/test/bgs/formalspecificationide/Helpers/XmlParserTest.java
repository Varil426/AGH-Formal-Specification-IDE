package bgs.formalspecificationide.Helpers;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

class XmlParserTest {

    @Test
    void parseXmlShop() {
        var useCases = XmlParser.parseXml(new File("xml_and_png_examples/1. GenMyModel/shop.xml"));
        var expectedResult = "{ship_order={EXTEND=[], INCLUDE=[], NAME=[Ship Order]}, create_order={EXTEND=[], INCLUDE=[], NAME=[Create Order]}, update_items={EXTEND=[], INCLUDE=[], NAME=[Update Items]}, register_details={EXTEND=[], INCLUDE=[], NAME=[Register Details]}}";
        assert useCases != null;
        assertEquals(expectedResult, useCases.toString());
    }

    @Test
    void parseXmlShopExt() {
        var useCases = XmlParser.parseXml(new File("xml_and_png_examples/1. GenMyModel/shop_ext.xml"));
        var expectedResult = "{ship_order={EXTEND=[register_details], INCLUDE=[], NAME=[Ship Order]}, create_order={EXTEND=[], INCLUDE=[], NAME=[Create Order]}, update_items={EXTEND=[], INCLUDE=[], NAME=[Update Items]}, register_details={EXTEND=[], INCLUDE=[create_order], NAME=[Register Details]}}";
        assert useCases != null;
        assertEquals(expectedResult, useCases.toString());
    }

    @Test
    void parseXmlBank() {
        var useCases = XmlParser.parseXml(new File("xml_and_png_examples/2. Papyrus/Bank.xml"));
        var expectedResult = "{wpłata_pieniedzy={EXTEND=[], INCLUDE=[], NAME=[Wpłata pieniedzy]}, wypłata_pieniędzy={EXTEND=[], INCLUDE=[], NAME=[Wypłata pieniędzy]}}";
        assert useCases != null;
        assertEquals(expectedResult, useCases.toString());
    }

    @Test
    void parseXmlBankExtend() {
        var useCases = XmlParser.parseXml(new File("xml_and_png_examples/2. Papyrus/Bank_extend.xml"));
        var expectedResult = "{usecase1={EXTEND=[], INCLUDE=[usecase2], NAME=[UseCase1]}, usecase2={EXTEND=[usecase3], INCLUDE=[], NAME=[UseCase2]}, usecase3={EXTEND=[], INCLUDE=[], NAME=[UseCase3]}}";
        assert useCases != null;
        assertEquals(expectedResult, useCases.toString());
    }
}