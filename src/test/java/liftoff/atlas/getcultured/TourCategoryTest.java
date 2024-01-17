package liftoff.atlas.getcultured;

import jakarta.persistence.OneToMany;
import liftoff.atlas.getcultured.models.Tour;
import liftoff.atlas.getcultured.models.TourCategory;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TourCategoryTest  extends AbstractTest{

    // Verifies the TourCategory.tours field is properly defined
    @Test
    public void TestToursFieldIsProperlyDefined() throws ClassNotFoundException, IllegalAccessException {
        Class tourCategoryClass = getClassByName("models.TourCategory");
        Field toursField = null;

        // Verify TourCategory.tours exists
        try {
            toursField = tourCategoryClass.getDeclaredField("tours");
        } catch (NoSuchFieldException e) {
            fail("Tour Category does not have a tours field");
        }

        // verify TourCategory.tours is of type List
        Type type = toursField.getType();
        assertEquals(List.class, type);

        // verifies TourCategory.tours is initially an empty ArrayList
        TourCategory tourCategory = new TourCategory();
        toursField.setAccessible(true);
        ArrayList<Tour> initializedList = (ArrayList<Tour>) toursField.get(tourCategory);

        for (Tour item : initializedList) {
            fail("tours should be initialized to an empty ArrayList");
        }

    }

    // Verifies that TourCategory.tours has @OneToMany with correct mappedBy value
    @Test
    public void testTourHasCorrectPersistenceAnnotations() throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class tourCategoryClass = getClassByName("models.TourCategory");
        Field toursField = tourCategoryClass.getDeclaredField("tours");
        Annotation annotation = toursField.getDeclaredAnnotation(OneToMany.class);
        assertNotNull(annotation);
        Method mappedByMethod = annotation.getClass().getMethod("mappedBy");
        assertEquals("tourCategory", mappedByMethod.invoke(annotation));
    }

    // Verifies that TourController has an @Autowired tourCategoryRepository field

}
