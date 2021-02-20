package com.vhddev.task1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "success",
        "location"
})

public class ResponseClass {

    @JsonProperty("success")
    public static List<Profile> profileList = null;
    @JsonProperty("location")
    public static List<Location> location = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("success")
    public List<Profile> getSuccess() {
        return profileList;
    }

    @JsonProperty("success")
    public void setSuccess(List<Profile> profileList) {
        this.profileList = profileList;
    }

    @JsonProperty("location")
    public List<Location> getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(List<Location> location) {
        this.location = location;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
