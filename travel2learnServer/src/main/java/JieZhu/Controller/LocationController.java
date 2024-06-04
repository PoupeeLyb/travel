package JieZhu.Controller;

//查询景点经纬度

import JieZhu.Service.LocationService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationController {
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/centerPoint/{sceneId}")
    public String getCenterPoint(@PathVariable int sceneId) {
        String locStr=locationService.getCoordinates(sceneId);
        System.out.println(locStr);
        return locStr;
    }
}
