package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"classpath:config/${device}.properties",
        "classpath:config/emulator.properties",
        "system:properties",})

public interface CredentialsConfig extends Config {

    @Key("deviceName")
    @DefaultValue("For_testing") // наименование эмулятора в AVD
    String deviceName();
    String platformName();
    @Key("platformVersion") // Версия андроида
    @DefaultValue("11.0")
    String platformVersion();

    // caps.setCapability: "browserstack.user"
    String user();
    // caps.setCapability: "browserstack.user"
    String key();
    // caps.setCapability: "app"
    String app();
    //URL("http://hub.browserstack.com/wd/hub");
    String url();



}
