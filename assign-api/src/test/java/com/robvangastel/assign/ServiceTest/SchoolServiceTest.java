package com.robvangastel.assign.ServiceTest;

import com.robvangastel.assign.dao.ISchoolDao;
import com.robvangastel.assign.domain.School;
import com.robvangastel.assign.domain.Study;
import com.robvangastel.assign.exception.SchoolException;
import com.robvangastel.assign.services.SchoolService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Rob van Gastel
 */
public class SchoolServiceTest {

    // EXAMPLE DESCRIPTION
    /***
     * TEST CASE METHOD
     *
     * Case: STATE TEST CASE
     *
     * Method:
     * STATE METHOD DESCRIPTION
     */

    @InjectMocks
    private SchoolService schoolService;

    @Mock
    private ISchoolDao schoolDao;

    @Before
    public void before() {
        initMocks(this);
    }

    /***
     * public Study addStudy(School school, String study)
     *
     * Case: Add study that doesn't exist yet.
     *
     */
    @Test
    public void addStudyTest1() {
        School school = new School("Fontys");
        school.setId(1L);
        String study = "ICT";


        when(schoolDao.doesStudyAlreadyExist(school.getId(), study)).thenReturn(false);
        schoolService.addStudy(school, study);

        verify(schoolDao, atLeastOnce()).createStudy(new Study(school, study));
        verify(schoolDao, atLeastOnce()).update(school);
    }

    /***
     * public Study addStudy(School school, String study)
     *
     * Case: Add study that already exist.
     *
     */
    @Test(expected=SchoolException.class)
    public void addStudyTest2() {
        School school = new School("Fontys");
        school.setId(1L);
        String study = "ICT";


        when(schoolDao.doesStudyAlreadyExist(school.getId(), study)).thenReturn(true);
        schoolService.addStudy(school, study);

        verify(schoolDao, never()).createStudy(new Study(school, study));
        verify(schoolDao, never()).update(school);
    }
}
