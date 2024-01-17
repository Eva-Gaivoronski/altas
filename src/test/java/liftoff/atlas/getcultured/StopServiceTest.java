package liftoff.atlas.getcultured;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import liftoff.atlas.getcultured.models.Stop;
import liftoff.atlas.getcultured.models.data.StopRepository;
import liftoff.atlas.getcultured.models.data.TourRepository;
import liftoff.atlas.getcultured.services.StopService;
import liftoff.atlas.getcultured.services.TourService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StopServiceTest {

    @Mock
    private StopRepository stopRepository;

    @InjectMocks
    private StopService stopService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


}
