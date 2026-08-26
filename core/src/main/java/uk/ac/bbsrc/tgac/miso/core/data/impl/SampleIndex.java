package uk.ac.bbsrc.tgac.miso.core.data.impl;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.persistence.*;
import uk.ac.bbsrc.tgac.miso.core.data.Deletable;
import uk.ac.bbsrc.tgac.miso.core.util.LimsUtils;

@Entity
public class SampleIndex implements Deletable, Serializable {

  private static final long serialVersionUID = 1L;
  private static final Long UNSAVED_ID = 0L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long indexId = UNSAVED_ID;

  private String name;

  private int position;

  private String sequence;

  private String realSequences;

  @ManyToOne
  @JoinColumn(name = "indexFamilyId")
  private SampleIndexFamily family;

  @Override
  public long getId() {
    return indexId;
  }

  @Override
  public void setId(long id) {
    this.indexId = id;
  }

  @Override
  public boolean isSaved() {
    return getId() != UNSAVED_ID;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPosition(int position) {
    this.position = position;
  }

  public void setSequence(String sequence) {
    this.sequence = sequence;
  }

  public SampleIndexFamily getFamily() {
    return family;
  }

  public void setFamily(SampleIndexFamily family) {
    this.family = family;
  }

  public int getPosition() {
    return position;
  }

  public String getSequence() {
    return sequence;
  }

  public Set<String> getRealSequences() {
    if (realSequences == null) {
      return Collections.emptySet();
    } else {
      return Arrays.stream(realSequences.split(",")).collect(Collectors.toUnmodifiableSet());
    }
  }

  public void setRealSequences(String realSequences) {
    if (LimsUtils.isStringEmptyOrNull(realSequences)) {
      this.realSequences = null;
    } else {
      this.realSequences = Arrays.stream(realSequences.split(",")).sorted().collect(Collectors.joining(","));
    }
  }

  public void setRealSequences(Set<String> realSequences) {
    if (realSequences == null || realSequences.isEmpty()) {
      this.realSequences = null;
    } else {
      this.realSequences = realSequences.stream().sorted().collect(Collectors.joining(","));
    }
  }

  @Override
  public String getDeleteType() {
    return "Sample Index";
  }

  @Override
  public String getDeleteDescription() {
    return getFamily().getName() + " - " + getName();
  }

}
