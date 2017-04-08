import org.junit.Assert;
import org.junit.Test;

public class IeeeMacRegisterTest {

    @Test
    public void testLargeLookup() {
        Assert.assertEquals(
                "Samsung Electronics Co.,Ltd",
                IeeeMacRegister.getManufacturer("C4:57:6E:8C:4B:F8"));
        Assert.assertEquals(
                "Slim Devices, Inc.",
                IeeeMacRegister.getManufacturer("000420281597"));
        Assert.assertEquals(
                "Critical Link LLC",
                IeeeMacRegister.getManufacturer("70:b3:d5:b5:1e:01"));
        Assert.assertEquals(
                "Motorola Mobility LLC, a Lenovo Company",
                IeeeMacRegister.getManufacturer("90:68:C3:E5:D1:39"));
        Assert.assertEquals(
                "Microsoft",
                IeeeMacRegister.getManufacturer("4C:0B:BE:40:95:C0"));

    }
}