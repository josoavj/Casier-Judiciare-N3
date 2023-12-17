package Bulletin.UI;

import Bulletin.persistence.infoCondamnation.InfoConsernedService;

public class InfoConsernedServiceInstance {
    public static InfoConsernedService infoConsernedServiceIns(){
        return InfoConsernedService.getInstance();
    }
}
