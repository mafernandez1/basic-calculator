package com.nodeweave.test.basiccalculatorrest;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BasicCalculatorRestApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void performExactSquareRoot() {
        try {
            mvc.perform(get("/calc/sqrt/4.0"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("2.0"));
        } catch (Exception ex) {
            Logger.getLogger(BasicCalculatorRestApplicationTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void performDecimalSquareRoot() {
        try {
            mvc.perform(get("/calc/sqrt/2.0"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("1.41"));
        } catch (Exception ex) {
            Logger.getLogger(BasicCalculatorRestApplicationTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void performRoundUpDecimalSquareRoot() {
        try {
            mvc.perform(get("/calc/sqrt/5.0"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("2.24"));
        } catch (Exception ex) {
            Logger.getLogger(BasicCalculatorRestApplicationTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void performSquareRootError() {
        try {
            mvc.perform(get("/calc/sqrt/100000000000000000000.0"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("E"));
        } catch (Exception ex) {
            Logger.getLogger(BasicCalculatorRestApplicationTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void divideExact() {
        try {
            mvc.perform(get("/calc/div/4.0/2.0"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("2.0"));
        } catch (Exception ex) {
            Logger.getLogger(BasicCalculatorRestApplicationTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void divideNotExact() {
        try {
            mvc.perform(get("/calc/div/-5.0/2.0"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("-2.5"));
        } catch (Exception ex) {
            Logger.getLogger(BasicCalculatorRestApplicationTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void divideRoundUpNotExact() {
        try {
            mvc.perform(get("/calc/div/7.0/-9.0"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("-0.78"));
        } catch (Exception ex) {
            Logger.getLogger(BasicCalculatorRestApplicationTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void zeroDivision() {
        try {
            mvc.perform(get("/calc/div/0.0/3.0"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("0.0"));
        } catch (Exception ex) {
            Logger.getLogger(BasicCalculatorRestApplicationTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void divideError() {
        try {
            mvc.perform(get("/calc/div/123456789933.0/2.0"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("E"));
        } catch (Exception ex) {
            Logger.getLogger(BasicCalculatorRestApplicationTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void multiply() {
        try {
            mvc.perform(get("/calc/mult/1.33/2.66"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("3.54"));
        } catch (Exception ex) {
            Logger.getLogger(BasicCalculatorRestApplicationTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void multiplyFirstNegative() {
        try {
            mvc.perform(get("/calc/mult/-1.33/2.66"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("-3.54"));
        } catch (Exception ex) {
            Logger.getLogger(BasicCalculatorRestApplicationTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void multiplySecondNegative() {
        try {
            mvc.perform(get("/calc/mult/1.33/-2.66"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("-3.54"));
        } catch (Exception ex) {
            Logger.getLogger(BasicCalculatorRestApplicationTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void multiplyBothNegative() {
        try {
            mvc.perform(get("/calc/mult/-1.33/-2.66"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("3.54"));
        } catch (Exception ex) {
            Logger.getLogger(BasicCalculatorRestApplicationTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void multiplyError() {
        try {
            mvc.perform(get("/calc/mult/123456789933.0/2.0"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("E"));
        } catch (Exception ex) {
            Logger.getLogger(BasicCalculatorRestApplicationTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void subtract() {
        try {
            mvc.perform(get("/calc/sub/2.66/1.33"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("1.33"));
        } catch (Exception ex) {
            Logger.getLogger(BasicCalculatorRestApplicationTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void subtractSameSign() {
        try {
            mvc.perform(get("/calc/sub/2.66/-1.33"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("3.99"));
        } catch (Exception ex) {
            Logger.getLogger(BasicCalculatorRestApplicationTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void subtractSameSignNegative() {
        try {
            mvc.perform(get("/calc/sub/-2.66/1.33"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("-3.99"));
        } catch (Exception ex) {
            Logger.getLogger(BasicCalculatorRestApplicationTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void subtractBothNegative() {
        try {
            mvc.perform(get("/calc/sub/-2.66/-1.33"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("-1.33"));
        } catch (Exception ex) {
            Logger.getLogger(BasicCalculatorRestApplicationTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void subtractError() {
        try {
            mvc.perform(get("/calc/sub/123456789933.0/1.0"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("E"));
        } catch (Exception ex) {
            Logger.getLogger(BasicCalculatorRestApplicationTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void add() {
        try {
            mvc.perform(get("/calc/add/2.66/1.33"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("3.99"));
        } catch (Exception ex) {
            Logger.getLogger(BasicCalculatorRestApplicationTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void addSameSign() {
        try {
            mvc.perform(get("/calc/add/2.66/-1.33"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("1.33"));
        } catch (Exception ex) {
            Logger.getLogger(BasicCalculatorRestApplicationTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void addSameSignNegative() {
        try {
            mvc.perform(get("/calc/add/-2.66/1.33"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("-1.33"));
        } catch (Exception ex) {
            Logger.getLogger(BasicCalculatorRestApplicationTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void addBothNegative() {
        try {
            mvc.perform(get("/calc/add/-2.66/-1.33"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("-3.99"));
        } catch (Exception ex) {
            Logger.getLogger(BasicCalculatorRestApplicationTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void addError() {
        try {
            mvc.perform(get("/calc/add/123456789933.0/2.0"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("E"));
        } catch (Exception ex) {
            Logger.getLogger(BasicCalculatorRestApplicationTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
