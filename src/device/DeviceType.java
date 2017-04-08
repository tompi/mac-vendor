package device;

public enum DeviceType {
    pc("f108"),
    tv("f26c"),
    radio("f001"),
    iphone("f10b"),
    ipad("f10a"),
    mac("f179"),
    android("f17b"),
    sonos("f001"),
    xbox("f11b"),
    playstation("f11b"),
    wii("f11b"),
    unknown("f109");

    private String fontAwesomeSign;

    DeviceType(String fontAwesomeSign) {
        this.fontAwesomeSign = fontAwesomeSign;
    }
}
