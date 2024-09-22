package shad.wegri.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shad.wegri.dto.PinAvailableSearchDto;
import shad.wegri.repository.PinRepository;
import shad.wegri.service.BicycleService;

@RestController
@RequestMapping("/api/maps/1/pins")
public class BicycleController {
    @Autowired
    BicycleService bicycleService;

    @PostMapping("/rental")
    public ResponseEntity<String> rentalBicycle(@RequestBody PinAvailableSearchDto request){
        if (bicycleService.availableBicycle(request.getPin_id()) == true) {
            return ResponseEntity.status(HttpStatus.OK)
                .body("rental success");
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("That bicycle already occupied.");
        }
    }
}
