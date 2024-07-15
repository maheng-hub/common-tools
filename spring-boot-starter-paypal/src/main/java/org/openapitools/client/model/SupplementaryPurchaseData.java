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
 * The capture identification-related fields. Includes the invoice ID, custom ID, note to payer, and soft descriptor.
 */
@JsonPropertyOrder({
  SupplementaryPurchaseData.JSON_PROPERTY_INVOICE_ID,
  SupplementaryPurchaseData.JSON_PROPERTY_NOTE_TO_PAYER
})
@JsonTypeName("supplementary_purchase_data")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-06-11T03:52:37.374153451Z[Atlantic/Reykjavik]")
public class SupplementaryPurchaseData {
  public static final String JSON_PROPERTY_INVOICE_ID = "invoice_id";
  private String invoiceId;

  public static final String JSON_PROPERTY_NOTE_TO_PAYER = "note_to_payer";
  private String noteToPayer;

  public SupplementaryPurchaseData() {
  }

  public SupplementaryPurchaseData invoiceId(String invoiceId) {
    
    this.invoiceId = invoiceId;
    return this;
  }

   /**
   * The API caller-provided external invoice number for this order. Appears in both the payer&#39;s transaction history and the emails that the payer receives.
   * @return invoiceId
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_INVOICE_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getInvoiceId() {
    return invoiceId;
  }


  @JsonProperty(JSON_PROPERTY_INVOICE_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setInvoiceId(String invoiceId) {
    this.invoiceId = invoiceId;
  }


  public SupplementaryPurchaseData noteToPayer(String noteToPayer) {
    
    this.noteToPayer = noteToPayer;
    return this;
  }

   /**
   * An informational note about this settlement. Appears in both the payer&#39;s transaction history and the emails that the payer receives.
   * @return noteToPayer
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_NOTE_TO_PAYER)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getNoteToPayer() {
    return noteToPayer;
  }


  @JsonProperty(JSON_PROPERTY_NOTE_TO_PAYER)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setNoteToPayer(String noteToPayer) {
    this.noteToPayer = noteToPayer;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SupplementaryPurchaseData supplementaryPurchaseData = (SupplementaryPurchaseData) o;
    return Objects.equals(this.invoiceId, supplementaryPurchaseData.invoiceId) &&
        Objects.equals(this.noteToPayer, supplementaryPurchaseData.noteToPayer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(invoiceId, noteToPayer);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SupplementaryPurchaseData {\n");
    sb.append("    invoiceId: ").append(toIndentedString(invoiceId)).append("\n");
    sb.append("    noteToPayer: ").append(toIndentedString(noteToPayer)).append("\n");
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

    // add `invoice_id` to the URL query string
    if (getInvoiceId() != null) {
      try {
        joiner.add(String.format("%sinvoice_id%s=%s", prefix, suffix, URLEncoder.encode(String.valueOf(getInvoiceId()), "UTF-8").replaceAll("\\+", "%20")));
      } catch (UnsupportedEncodingException e) {
        // Should never happen, UTF-8 is always supported
        throw new RuntimeException(e);
      }
    }

    // add `note_to_payer` to the URL query string
    if (getNoteToPayer() != null) {
      try {
        joiner.add(String.format("%snote_to_payer%s=%s", prefix, suffix, URLEncoder.encode(String.valueOf(getNoteToPayer()), "UTF-8").replaceAll("\\+", "%20")));
      } catch (UnsupportedEncodingException e) {
        // Should never happen, UTF-8 is always supported
        throw new RuntimeException(e);
      }
    }

    return joiner.toString();
  }

}
