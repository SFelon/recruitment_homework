package com.recruitment.homework.model.entity;

public interface VersionedEntity extends HasId {
    Integer getVersion();
}
