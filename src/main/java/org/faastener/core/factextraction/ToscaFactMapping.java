package org.faastener.core.factextraction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.faastener.core.model.common.FilterType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToscaFactMapping {
    String factId;
    String criterionId;
    FilterType filterType;
}
