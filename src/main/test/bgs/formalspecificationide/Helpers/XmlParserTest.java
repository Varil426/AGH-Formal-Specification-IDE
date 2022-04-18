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

    @Test
    void parseXmlVpEx1() {
        var useCases = XmlParser.parseXml(new File("xml_and_png_examples/3. Visual Paradigm/ex1/project1.xml"));
        var expectedResult = "{invest_money={EXTEND=[], INCLUDE=[], NAME=[Invest Money]}, manage_accounts={EXTEND=[], INCLUDE=[], NAME=[Manage Accounts]}, loan_money={EXTEND=[], INCLUDE=[], NAME=[Loan Money]}, manage_credit_account={EXTEND=[], INCLUDE=[], NAME=[Manage Credit Account]}}";
        assert useCases != null;
        assertEquals(expectedResult, useCases.toString());
    }

    @Test
    void parseXmlVpEx2() {
        var useCases = XmlParser.parseXml(new File("xml_and_png_examples/3. Visual Paradigm/ex2/project2.xml"));
        var expectedResult = "{repair={EXTEND=[], INCLUDE=[], NAME=[Repair]}, transfer_funds={EXTEND=[], INCLUDE=[], NAME=[Transfer Funds]}, deposit_funds={EXTEND=[], INCLUDE=[], NAME=[Deposit Funds]}, withdraw_cash={EXTEND=[], INCLUDE=[], NAME=[Withdraw Cash]}, maintenance={EXTEND=[], INCLUDE=[], NAME=[Maintenance]}, check_balances={EXTEND=[], INCLUDE=[], NAME=[Check Balances]}}";
        assert useCases != null;
        assertEquals(expectedResult, useCases.toString());
    }

    @Test
    void parseXmlVpEx3() {
        var useCases = XmlParser.parseXml(new File("xml_and_png_examples/3. Visual Paradigm/ex3/project3_vp_simple_xml_format.xml"));
        var expectedResult = "{evaluate_software_components={EXTEND=[], INCLUDE=[], NAME=[Evaluate Software Components]}, identify_software_requirements={EXTEND=[], INCLUDE=[], NAME=[Identify Software Requirements]}, define_software_deliverables={EXTEND=[], INCLUDE=[], NAME=[Define Software Deliverables]}, plan_software_production={EXTEND=[], INCLUDE=[], NAME=[Plan Software Production]}, evaluate_software_product={EXTEND=[], INCLUDE=[], NAME=[Evaluate Software Product]}, identify_software_components={EXTEND=[], INCLUDE=[], NAME=[Identify Software Components]}}";
        assert useCases != null;
        assertEquals(expectedResult, useCases.toString());
    }

    @Test
    void parseXmlVpEx3TraditionalXml() {
        var useCases = XmlParser.parseXml(new File("xml_and_png_examples/3. Visual Paradigm/ex3_traditional_xml/project_3_vp_traditional_xml_format.xml"));
        var expectedResult = "{evaluate_software_components={EXTEND=[], INCLUDE=[], NAME=[Evaluate Software Components]}, identify_software_requirements={EXTEND=[], INCLUDE=[], NAME=[Identify Software Requirements]}, define_software_deliverables={EXTEND=[], INCLUDE=[], NAME=[Define Software Deliverables]}, plan_software_production={EXTEND=[], INCLUDE=[], NAME=[Plan Software Production]}, evaluate_software_product={EXTEND=[], INCLUDE=[], NAME=[Evaluate Software Product]}, identify_software_components={EXTEND=[], INCLUDE=[], NAME=[Identify Software Components]}}";
        assert useCases != null;
        assertEquals(expectedResult, useCases.toString());
    }

    @Test
    void parseXmlVpExtendInclude() {
        var useCases = XmlParser.parseXml(new File("xml_and_png_examples/3. Visual Paradigm/extend_include/project4.xml"));
        var expectedResult = "{transfer_money={EXTEND=[], INCLUDE=[login], NAME=[Transfer Money]}, pay_bills={EXTEND=[], INCLUDE=[login], NAME=[Pay Bills]}, donate_money_to_charity={EXTEND=[], INCLUDE=[login], NAME=[Donate Money to Charity]}, handle_abort={EXTEND=[login], INCLUDE=[], NAME=[Handle Abort]}, withdraw_cash={EXTEND=[], INCLUDE=[login], NAME=[Withdraw Cash]}, handle_invalid_password={EXTEND=[login], INCLUDE=[], NAME=[Handle Invalid Password]}, login={EXTEND=[], INCLUDE=[], NAME=[Login]}, check_balance={EXTEND=[], INCLUDE=[login], NAME=[Check Balance]}}";
        assert useCases != null;
        assertEquals(expectedResult, useCases.toString());
    }
}