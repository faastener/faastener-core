package org.faastener.core.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.faastener.core.model.common.EntityMapper;
import org.faastener.core.model.common.TechnologyType;
import org.faastener.core.model.domain.ClassificationFramework;
import org.faastener.core.model.entities.ClassificationFrameworkEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EntityMapperTest {
    private final EntityMapper entityMapper = new EntityMapper(new ModelMapper());

    @Test
    @DisplayName("Test toFrameworkEntity - Success")
    public void testConvertToFrameworkEntitySuccess() {
        List<ClassificationFramework.CriterionType> mockCriteria = new ArrayList<>();
        mockCriteria.add(new ClassificationFramework.CriterionType("criterion1", "criterion type 1", "criterion type 1 description", Collections.emptyList()));
        mockCriteria.add(new ClassificationFramework.CriterionType("criterion1", "criterion type 2", "criterion type 2 description", Collections.emptyList()));
        ClassificationFramework.CriteriaGrouping mockGrouping = new ClassificationFramework.CriteriaGrouping("groupingId", "test grouping", "grouping description", Collections.emptyList(), mockCriteria);
        ClassificationFramework.FrameworkView mockView = new ClassificationFramework.FrameworkView("viewId", "test view", "view description", Collections.singletonList(mockGrouping));
        ClassificationFramework mockFramework = new ClassificationFramework("frameworkId", "test", TechnologyType.FAAS, "1.0", "some description", Collections.singletonList(mockView));

        ClassificationFrameworkEntity frameworkEntity = entityMapper.toFrameworkEntity(mockFramework);

        assertEquals(mockFramework.getId(), frameworkEntity.getId());
        assertEquals(mockFramework.getFrameworkViews().get(0).getId(), frameworkEntity.getFrameworkViews().get(0).getId());
    }

    @Test
    @DisplayName("Test toFrameworkDomainModel - Success")
    public void testConvertToFrameworkDomainModelSuccess() {
        List<ClassificationFrameworkEntity.CriterionTypeEntity> mockCriteria = new ArrayList<>();
        mockCriteria.add(new ClassificationFrameworkEntity.CriterionTypeEntity("criterion1", "criterion type 1", "criterion type 1 description", Collections.emptyList()));
        mockCriteria.add(new ClassificationFrameworkEntity.CriterionTypeEntity("criterion1", "criterion type 2", "criterion type 2 description", Collections.emptyList()));
        ClassificationFrameworkEntity.CriteriaGroupingEntity mockGrouping = new ClassificationFrameworkEntity.CriteriaGroupingEntity("groupingId", "test grouping", "grouping description", Collections.emptyList(), mockCriteria);
        ClassificationFrameworkEntity.FrameworkViewEntity mockView = new ClassificationFrameworkEntity.FrameworkViewEntity("viewId", "test view", "view description", Collections.singletonList(mockGrouping));
        ClassificationFrameworkEntity mockFrameworkEntity = new ClassificationFrameworkEntity("frameworkId", "test", TechnologyType.FAAS, "1.0", "some description", Collections.singletonList(mockView));

        ClassificationFramework framework = entityMapper.toFrameworkDomainModel(mockFrameworkEntity);

        assertEquals(mockFrameworkEntity.getId(), framework.getId());
        assertEquals(mockFrameworkEntity.getFrameworkViews().get(0).getId(), framework.getFrameworkViews().get(0).getId());
    }
}
