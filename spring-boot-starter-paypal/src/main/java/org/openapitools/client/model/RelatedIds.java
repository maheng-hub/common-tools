/*
 * Payments
 * Call the Payments API to authorize payments, capture authorized payments, refund payments that have already been captured, and show payment information. Use the Payments API in conjunction with the <a href=\"/docs/api/orders/v2/\">Orders API</a>. For more information, see the <a href=\"/docs/checkout/\">PayPal Checkout Overview</a>.
 *
 * The version of the OpenAPI document: 2.5
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.openapitools.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.StringJoiner;

/**
 * Identifiers related to a specific resource.
 */
@JsonPropertyOrder({
  RelatedIds.JSON_PROPERTY_ORDER_ID,
  RelatedIds.JSON_PROPERTY_AUTHORIZATION_ID,
  RelatedIds.JSON_PROPERTY_CAPTURE_ID
})
@JsonTypeName("related_ids")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-06-11T03:52:37.374153451Z[Atlantic/Reykjavik]")
public class RelatedIds {
  public static final String JSON_PROPERTY_ORDER_ID = "order_id";
  private String orderId;

  public static final String JSON_PROPERTY_AUTHORIZATION_ID = "authorization_id";
  private String authorizationId;

  public static final String JSON_PROPERTY_CAPTURE_ID = "capture_id";
  private String captureId;

  public RelatedIds() {
  }

  public RelatedIds orderId(String orderId) {
    
    this.orderId = orderId;
    return this;
  }

   /**
   * Order ID related to the resource.
   * @return orderId
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_ORDER_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getOrderId() {
    return orderId;
  }


  @JsonProperty(JSON_PROPERTY_ORDER_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }


  public RelatedIds authorizationId(String authorizationId) {
    
    this.authorizationId = authorizationId;
    return this;
  }

   /**
   * Authorization ID related to the resource.
   * @return authorizationId
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_AUTHORIZATION_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getAuthorizationId() {
    return authorizationId;
  }


  @JsonProperty(JSON_PROPERTY_AUTHORIZATION_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setAuthorizationId(String authorizationId) {
    this.authorizationId = authorizationId;
  }


  public RelatedIds captureId(String captureId) {
    
    this.captureId = captureId;
    return this;
  }

   /**
   * Capture ID related to the resource.
   * @return captureId
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_CAPTURE_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getCaptureId() {
    return captureId;
  }


  @JsonProperty(JSON_PROPERTY_CAPTURE_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCaptureId(String captureId) {
    this.captureId = captureId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RelatedIds relatedIds = (RelatedIds) o;
    return Objects.equals(this.orderId, relatedIds.orderId) &&
        Objects.equals(this.authorizationId, relatedIds.authorizationId) &&
        Objects.equals(this.captureId, relatedIds.captureId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(orderId, authorizationId, captureId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RelatedIds {\n");
    sb.append("    orderId: ").append(toIndentedString(orderId)).append("\n");
    sb.append("    authorizationId: ").append(toIndentedString(authorizationId)).append("\n");
    sb.append("    captureId: ").append(toIndentedString(captureId)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

  /**
   * Convert the instance into URL query string.
   *
   * @return URL query string
   */
  public String toUrlQueryString() {
    return toUrlQueryString(null);
  }

  /**
   * Convert the instance into URL query string.
   *
   * @param prefix prefix of the query string
   * @return URL query string
   */
  public String toUrlQueryString(String prefix) {
    String suffix = "";
    String containerSuffix = "";
    String containerPrefix = "";
    if (prefix == null) {
      // style=form, explode=true, e.g. /pet?name=cat&type=manx
      prefix = "";
    } else {
      // deepObject style e.g. /pet?id[name]=cat&id[type]=manx
      prefix = prefix + "[";
      suffix = "]";
      containerSuffix = "]";
      containerPrefix = "[";
    }

    StringJoiner joiner = new StringJoiner("&");

    // add `order_id` to the URL query string
    if (getOrderId() != null) {
      try {
        joiner.add(String.format("%sorder_id%s=%s", prefix, suffix, URLEncoder.encode(String.valueOf(getOrderId()), "UTF-8").replaceAll("\\+", "%20")));
      } catch (UnsupportedEncodingException e) {
        // Should never happen, UTF-8 is always supported
        throw new RuntimeException(e);
      }
    }

    // add `authorization_id` to the URL query string
    if (getAuthorizationId() != null) {
      try {
        joiner.add(String.format("%sauthorization_id%s=%s", prefix, suffix, URLEncoder.encode(String.valueOf(getAuthorizationId()), "UTF-8").replaceAll("\\+", "%20")));
      } catch (UnsupportedEncodingException e) {
        // Should never happen, UTF-8 is always supported
        throw new RuntimeException(e);
      }
    }

    // add `capture_id` to the URL query string
    if (getCaptureId() != null) {
      try {
        joiner.add(String.format("%scapture_id%s=%s", prefix, suffix, URLEncoder.encode(String.valueOf(getCaptureId()), "UTF-8").replaceAll("\\+", "%20")));
      } catch (UnsupportedEncodingException e) {
        // Should never happen, UTF-8 is always supported
        throw new RuntimeException(e);
      }
    }

    return joiner.toString();
  }

}

