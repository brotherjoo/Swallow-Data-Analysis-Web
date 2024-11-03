package org.swallow.swallow_data_analysis.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "swallow_table")
@Data
@NoArgsConstructor
public class SwallowTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;

    @OneToMany(mappedBy = "swallow")
    private List<Swallow> swallows = new ArrayList<>();
}
