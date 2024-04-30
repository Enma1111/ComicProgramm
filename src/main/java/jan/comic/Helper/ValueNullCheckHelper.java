package jan.comic.Helper;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class ValueNullCheckHelper {

    public String[] comicValueChecker(String @NotNull [] val){

        for (int i = 0; i < val.length; i++) {
            if (i == 1) {
                val[i] = val[i].isEmpty() ? "'" + val[i] + "'" : "Einzelband";
            } else if (i == 2) {
                val[i] = val[i].isEmpty() ? "'" + val[i] + "'" : "Offen";
            } else {
                val[i] = val[i] != null ? "'" + val[i] + "'" : "NULL";
            }

        }
        return val;
    }

    public String[] movieValueChecker(String @NotNull [] val){

        for (int i = 0; i < val.length; i++) {
            val[i] = val[i] != null ? "'" + val[i] + "'" : "NULL";
        }
        return val;
    }

    public String[] bookValueChecker(String @NotNull [] val){

        for (int i = 0; i < val.length; i++) {
            val[i] = val[i] != null ? "'" + val[i] + "'" : "NULL";
        }
        return val;
    }
}
