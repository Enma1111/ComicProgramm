package jan.comic.OverController;

import jan.comic.Scene.NewScene;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OverlordOptionViewConfigurator {

    private final NewScene newScene;
    private final String comicViewName;
    private final String comicViewFxml;
    private final String movieViewName;
    private final String movieViewFxml;
    private final String bookViewName;
    private final String bookViewFXML;

    @Autowired
    public OverlordOptionViewConfigurator(NewScene newScene, String comicViewName, String comicViewFxml,
                                          String movieViewName, String movieViewFxml, String bookViewName,
                                          String bookViewFXML) {
        this.newScene = newScene;
        this.comicViewName = comicViewName;
        this.comicViewFxml = comicViewFxml;
        this.movieViewName = movieViewName;
        this.movieViewFxml = movieViewFxml;
        this.bookViewName = bookViewName;
        this.bookViewFXML = bookViewFXML;
    }

}
