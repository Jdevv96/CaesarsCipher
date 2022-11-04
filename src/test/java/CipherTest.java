import org.testng.Assert;
import org.testng.annotations.Test;

public class CipherTest {

    CipherApp app = new CipherApp();

    @Test
    public void emptyMessage_ReturnsEmptyString() {
        Assert.assertEquals("", app.cipherMessage("", 10));
    }

    @Test
    public void lowercase_NegativeOffset_ReturnsCorrectStr() {
        Assert.assertEquals("fg", app.cipherMessage("hi", -2));
    }

    @Test
    public void uppercase_NegativeOffset_ReturnsCorrectStr() {
        Assert.assertEquals("FG", app.cipherMessage("HI", -2));
    }

    @Test
    public void lowercase_HiOffsetByTwo_Returns_jk() {
        Assert.assertEquals("jk", app.cipherMessage("hi", 2));
    }

    @Test
    public void uppercase_HiOffsetByTwo_Returns_jk() {
        Assert.assertEquals("JK", app.cipherMessage("HI", 2));
    }

    @Test
    public void lowercase_MessageWithPunctuation_ReturnsUnmovedPunctuation() {
        Assert.assertEquals("mn. fsi dtz?", app.cipherMessage("hi. and you?", 5));
    }

    @Test
    public void uppercase_MessageWithPunctuation_ReturnsUnmovedPunctuation() {
        Assert.assertEquals("MN. FSI DTZ?", app.cipherMessage("HI. AND YOU?", 5));
    }

    @Test
    public void lowercase_OffsetGreaterThanAlphaSize_ReturnsCorrectStr() {
        Assert.assertEquals("lic! csy?", app.cipherMessage("hey! you?", 30));
    }

    @Test
    public void uppercase_OffsetGreaterThanAlphaSize_ReturnsCorrectStr() {
        Assert.assertEquals("LIC! CSY?", app.cipherMessage("HEY! YOU?", 30));
    }

    @Test
    public void StringWithNumbers_ReturnsNumbersIgnored() {
        Assert.assertEquals("Lql gWc 4omb Bw Ktwks qV 11?", app.cipherMessage("Did yOu 4get To Clock iN 11?", 8));
    }

    @Test
    public void StringWithSpecialChars_ReturnsSpecialCharsIgnored() {
        Assert.assertEquals("*** Wi xkwo sc @Tkuo!!", app.cipherMessage("*** My name is @Jake!!", 10));
    }

}
