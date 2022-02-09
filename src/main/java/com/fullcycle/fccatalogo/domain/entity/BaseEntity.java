package com.fullcycle.fccatalogo.domain.entity;

import java.util.UUID;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public class BaseEntity {
    
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(columnDefinition = "VARBINARY(16)")
    private UUID idUUID;

    public Integer getId(){
        return this.id;
    }

    public void setId(Integer id){
        if(id == null) throw new IllegalArgumentException("id is marked non-null but is null");
        this.id = id;
    }

    public UUID getIdUUID(){
        return this.idUUID;
    }

    protected UUID generateUUID() {
        this.idUUID = UUID.randomUUID();
        return this.idUUID;
    }

    public void setIdUUID(UUID idUUID){
        if(idUUID == null) throw new IllegalArgumentException("id UUID is marked non-null but is null");
        if(Boolean.FALSE.equals(this.isValidUUID(idUUID.toString()))) throw new IllegalArgumentException("id UUID is marked as valid UUID but is not valid UUID");
        this.idUUID = idUUID;
    }

    protected Boolean isValidUUID(String idUUID) {
        if(idUUID == null) {
            return false;
        }
        final Pattern patten= Pattern.compile("^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$");
        return patten.matcher(idUUID).matches();
    }
}
