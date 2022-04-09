package cloud.autotests.config.demowebshop;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/ozon/app.properties"
})
public interface AppConfig extends Config {
    @Key("webUrl")
    String webUrl();
    @Key("apiUrl")
    String apiUrl();
    String userLogin();
    String userPassword();

}
