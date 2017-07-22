package service.response;

import service.serializer.serialize.JsonIgnore;
import service.serializer.serialize.JsonSerializer;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static play.libs.Json.toJson;

public class ServiceResponse {

    @JsonIgnore
    protected JsonSerializer jsonSerializer;

    @JsonIgnore
    protected short status;

    @JsonIgnore
    protected String message;

    @JsonIgnore
    protected long responseTime;

    public ServiceResponse() {
        this.status = StatusCode.SYSTEM_BAD_REQUEST;
        this.message = toJson("{}").toString();
        this.responseTime = new Date().getTime();
    }

    public ServiceResponse(short code) {
        this.status = code;
        this.message = "";
    }

    @SuppressWarnings("unchecked")
    public <T extends ServiceResponse> T invalidParam() {
        this.status = StatusCode.SYSTEM_PARAM_INVALID;
        this.message = "";
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public <T extends ServiceResponse> T invalidParamWithMessage(String message) {
        this.status = StatusCode.SYSTEM_PARAM_INVALID;
        this.message = message;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public <T extends ServiceResponse> T invalidParamWithSerializer(JsonSerializer serializer) {
        this.status = StatusCode.SYSTEM_PARAM_INVALID;
        this.message = "";
        this.jsonSerializer = serializer;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public <T extends ServiceResponse> T serverError() {
        this.status = StatusCode.SYSTEM_SERVER_ERROR;
        this.message = "";
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public <T extends ServiceResponse> T serverErrorWithMessage(String message) {
        this.status = StatusCode.SYSTEM_SERVER_ERROR;
        this.message = message;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public <T extends ServiceResponse> T serverErrorWithSerializer(JsonSerializer serializer) {
        this.status = StatusCode.SYSTEM_SERVER_ERROR;
        this.message = "";
        this.jsonSerializer = serializer;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public <T extends ServiceResponse> T ok() {
        this.status = StatusCode.SYSTEM_OK;
        this.message = "";
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public <T extends ServiceResponse> T okWithMessage(String message) {
        this.status = StatusCode.SYSTEM_OK;
        this.message = message;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public <T extends ServiceResponse> T okWithSerializer(JsonSerializer serializer) {
        this.status = StatusCode.SYSTEM_OK;
        this.message = "";
        this.jsonSerializer = serializer;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public <T extends ServiceResponse> T withResponseTime(long responseTime) {
        this.responseTime = responseTime;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public <T extends ServiceResponse> T withStatusCode(short statusCode) {
        this.status = statusCode;
        this.message = "";
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public <T extends ServiceResponse> T withStatus(short statusCode) {
        this.status = statusCode;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public <T extends ServiceResponse> T withMessage(String message) {
        this.message = message;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public <T extends ServiceResponse> T withSerializer(JsonSerializer jsonSerializer) {
        this.jsonSerializer = jsonSerializer;
        return (T) this;
    }

    public boolean isOk() {
        return status == StatusCode.SYSTEM_OK;
    }

    public boolean isNotOk() {
        return status != StatusCode.SYSTEM_OK;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JsonSerializer getSerializer() {
        return jsonSerializer;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }

    public long getResponseTime() {
        return responseTime;
    }

    private Map<String, Object> prepareForWrite() {
        final Map<String, Object> response = new HashMap<>();

        response.put("status", status);
        response.put("message", message);
        response.put("responseTime", responseTime);
        response.put("body", this);
        return response;
    }

    private String writeWithOption() {
        if (jsonSerializer == null) throw new NullPointerException("jsonSerializer");
        final Map<String, Object> response = prepareForWrite();
        return jsonSerializer.toJson(response);
    }

    public String write() {
        return writeWithOption();
    }

}
