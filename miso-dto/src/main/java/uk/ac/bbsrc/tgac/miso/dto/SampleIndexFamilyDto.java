package uk.ac.bbsrc.tgac.miso.dto;

import static uk.ac.bbsrc.tgac.miso.dto.Dtos.*;

import java.util.List;

import uk.ac.bbsrc.tgac.miso.core.data.LibraryIndexFamily;
import uk.ac.bbsrc.tgac.miso.core.data.impl.SampleIndexFamily;
import uk.ac.bbsrc.tgac.miso.core.data.type.PlatformType;

import javax.annotation.Nonnull;

public class SampleIndexFamilyDto {
  private boolean archived;
  private Long id;
  private String name;
  private List<SampleIndexDto> indices;
  private String platformType;
  private boolean fakeSequence;
  private boolean uniqueDualIndex;

  public static SampleIndexFamilyDto from(SampleIndexFamily from, boolean includeChildren) {
    SampleIndexFamilyDto to = new SampleIndexFamilyDto();
    setLong(to::setId, from.getId(), true);
    setString(to::setName, from.getName());
    setString(to::setPlatformType, maybeGetProperty(from.getPlatformType(), PlatformType::name));
    setBoolean(to::setFakeSequence, from.hasFakeSequence(), false);
    setBoolean(to::setUniqueDualIndex, from.isUniqueDualIndex(), false);
    setBoolean(to::setArchived, from.getArchived(), false);
    if (includeChildren) {
      to.setIndices(from.getIndices().stream().map(SampleIndexDto::from).toList());
    }
    return to;
  }

  public static SampleIndexFamilyDto from(@Nonnull SampleIndexFamily from) {
    return from(from, true);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPlatformType() {
    return platformType;
  }

  public boolean getFakeSequence() {
    return fakeSequence;
  }

  public boolean isArchived() {
    return archived;
  }

  public boolean isUniqueDualIndex() {
    return uniqueDualIndex;
  }

  public void setArchived(boolean archived) {
    this.archived = archived;
  }

  public void setPlatformType(String platformType) {
    this.platformType = platformType;
  }

  public void setFakeSequence(boolean fake) {
    this.fakeSequence = fake;
  }

  public void setUniqueDualIndex(boolean uniqueDualIndex) {
    this.uniqueDualIndex = uniqueDualIndex;
  }

  public List<SampleIndexDto> getIndices() {
    return indices;
  }

  public void setIndices(List<SampleIndexDto> indices) {
    this.indices = indices;
  }

  public SampleIndexFamily to() {
    SampleIndexFamily to = new SampleIndexFamily();
    setLong(to::setId, getId(), false);
    setString(to::setName, getName());
    setObject(to::setPlatformType, getPlatformType(), PlatformType::valueOf);
    setBoolean(to::setFake, getFakeSequence(), false);
    setBoolean(to::setUniqueDualIndex, isUniqueDualIndex(), false);
    setBoolean(to::setArchived, isArchived(), true);
    to.setIndices(getIndices().stream().map(SampleIndexDto::to).toList());
    return to;
  }

}
