/*
 * MIT License
 *
 * Copyright (c) 2017-2018 Frederik Ar. Mikkelsen
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package fredboat.definitions;

import java.util.*;

/**
 * Created by napster on 21.03.18.
 */
public enum Language {
    EN_US("en", "US", "English", "English"),
    AF_ZA("af", "ZA", "Afrikaans", "Afrikaans"),
    AR_SA("ar", "SA", "ﺔﻴﺐﺮﻌﻠﺍ", "Arabic"),
    BG_BG("bg", "BG", "български език", "Bulgarian"),
    CA_ES("ca", "ES", "Catalan", "Catalan"),
    ZH_CN("zh", "CN", "简体中文", "Chinese Simplified"),
    ZH_TW("zh", "TW", "繁體中文", "Chinese Traditional"),
    HR_HR("hr", "HR", "Hrvatski", "Croatian"),
    CS_CZ("cs", "CZ", "Čeština", "Czech"),
    DA_DK("da", "DK", "Dansk", "Danish"),
    NL_NL("nl", "NL", "Nederlands", "Dutch"),
    FI_FI("fi", "FI", "suomi", "Finnish"),
    FIL_PH("fil", "PH", "Filipino", "Filipino"),
    FR_FR("fr", "FR", "Français", "French", "baguette"),
    DE_DE("de", "DE", "Deutsch", "German", "bratwurst", "lederhosen"),
    EL_GR("el", "GR", "ελληνικά", "Greek"),
    HE_IL("he", "IL", "עברית", "Hebrew"),
    HU_HU("hu", "HU", "magyar", "Hungarian"),
    ID_ID("id", "ID", "Bahasa Indonesia", "Indonesian"),
    IT_IT("it", "IT", "Italiano", "Italian"),
    JA_JP("ja", "JP", "日本語", "Japanese"),
    KO_KR("ko", "KR", "한국어", "Korean"),
    MS_MY("ms", "MY", "Bahasa Melayu", "Malay"),
    NO_NO("no", "NO", "Norsk", "Norwegian"),
    EN_PT("en", "PT", "Pirate", "Pirate English", "arrr"),
    PL_PL("pl", "PL", "Polski", "Polish"),
    PT_PT("pt", "PT", "Português", "Portuguese"),
    PT_BR("pt", "BR", "Português brasileiro", "Brazilian Portuguese", "Brazilian", "huehuehue"),
    RO_RO("ro", "RO", "Română", "Romanian"),
    RU_RU("ru", "RU", "Русский", "Russian", "cyka blyat", "cheeki breeki", "rush b"),
    ES_ES("es", "ES", "Español", "Spanish"),
    SV_SE("sv", "SE", "Svenska", "Swedish"),
    EN_TS("en", "TS", "Tsundere", "Tsundere English", "baka"),
    TR_TR("tr", "TR", "Türkçe", "Turkish"),
    TH_TH("th", "TH", "ไทย", "Thai"),
    VI_VN("vi", "VN", "Tiếng Việt", "Vietnamese"),
    CY_GB("cy", "GB", "Cymraeg", "Welsh"),
    //
    ;

    private final String code;
    private final Locale locale;
    private final String nativeName;
    private final String englishName;
    private final List<String> other; //other possible identifications / names for that lang (memes go here)

    Language(String language, String country, String nativeName, String englishName, String... other) {

        this.code = language + "_" + country;
        this.locale = new Locale(language, country);
        this.nativeName = nativeName;
        this.englishName = englishName;
        this.other = new IgnoreCaseStringList(Arrays.asList(other));
    }

    public String getCode() {
        return code;
    }

    public Locale getLocale() {
        return locale;
    }

    public String getNativeName() {
        return nativeName;
    }

    public String getEnglishName() {
        return englishName;
    }

    /**
     * This method tries to parse an input into a language that we recognize.
     * It will try to make use of the language code, the native language name, and the english name of a language to
     * match the input to a known language.
     *
     * @param input input to be parsed into a Language known to us (= defined in this enum)
     * @return the optional language identified from the input.
     */
    public static Optional<Language> parse(String input) {
        for (Language language : Language.values()) {
            if (language.name().equalsIgnoreCase(input)
                    || language.getCode().equalsIgnoreCase(input)
                    || language.getNativeName().equalsIgnoreCase(input)
                    || language.getEnglishName().equalsIgnoreCase(input)
                    || language.other.contains(input)) {
                return Optional.of(language);
            }
        }

        return Optional.empty();
    }

    private static class IgnoreCaseStringList extends ArrayList<String> {
        private static final long serialVersionUID = -7506969278886990540L;

        public IgnoreCaseStringList(Collection<? extends String> c) {
            super(c);
        }

        @Override
        public boolean contains(Object o) {
            String paramStr = (String) o;
            for (String s : this) {
                if (paramStr.equalsIgnoreCase(s)) return true;
            }
            return false;
        }
    }
}
