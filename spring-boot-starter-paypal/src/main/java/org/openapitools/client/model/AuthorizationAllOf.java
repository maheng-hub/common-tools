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
import java.util.ArrayList;
import java.util.List;
import org.openapitools.client.model.LinkDescription;
import org.openapitools.client.model.Money;
import org.openapitools.client.model.NetworkTransactionReference;
import org.openapitools.client.model.SellerProtection;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.StringJoiner;

/**
 * AuthorizationAllOf
 */
@JsonPropertyOrder({
  AuthorizationAllOf.JSON_PROPERTY_ID,
  AuthorizationAllOf.JSON_PROPERTY_AMOUNT,
  AuthorizationAllOf.JSON_PROPERTY_INVOICE_ID,
  AuthorizationAllOf.JSON_PROPERTY_CUSTOM_ID,
  AuthorizationAllOf.JSON_PROPERTY_NETWORK_TRANSACTION_REFERENCE,
  AuthorizationAllOf.JSON_PROPERTY_SELLER_PROTECTION,
  AuthorizationAllOf.JSON_PROPERTY_EXPIRATION_TIME,
  AuthorizationAllOf.JSON_PROPERTY_LINKS
})
@JsonTypeName("authorization_allOf")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-06-11T03:52:37.374153451Z[Atlantic/Reykjavik]")
public class AuthorizationAllOf {
  public static final String JSON_PROPERTY_ID = "id";
  private String id;

  public static final String JSON_PROPERTY_AMOUNT = "amount";
  private Money amount;

  public static final String JSON_PROPERTY_INVOICE_ID = "invoice_id";
  private String invoiceId;

  public static final String JSON_PROPERTY_CUSTOM_ID = "custom_id";
  private String customId;

  public static final String JSON_PROPERTY_NETWORK_TRANSACTION_REFERENCE = "network_transaction_reference";
  private NetworkTransactionReference networkTransactionReference;

  public static final String JSON_PROPERTY_SELLER_PROTECTION = "seller_protection";
  private SellerProtection sellerProtection;

  public static final String JSON_PROPERTY_EXPIRATION_TIME = "expiration_time";
  private String expirationTime;

  public static final String JSON_PROPERTY_LINKS = "links";
  private List<LinkDescription> links;

  public AuthorizationAllOf() {
  }

  @JsonCreator
  public AuthorizationAllOf(
    @JsonProperty(JSON_PROPERTY_ID) String id, 
    @JsonProperty(JSON_PROPERTY_INVOICE_ID) String invoiceId, 
    @JsonProperty(JSON_PROPERTY_LINKS) List<LinkDescription> links
  ) {
    this();
    this.id = id;
    this.invoiceId = invoiceId;
    this.links = links;
  }

   /**
   * The PayPal-generated ID for the authorized payment.
   * @return id
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getId() {
    return id;
  }




  public AuthorizationAllOf amount(Money amount) {
    
    this.amount = amount;
    return this;
  }

   /**
   * Get amount
   * @return amount
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_AMOUNT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Money getAmount() {
    return amount;
  }


  @JsonProperty(JSON_PROPERTY_AMOUNT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setAmount(Money amount) {
    this.amount = amount;
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




  public AuthorizationAllOf customId(String customId) {
    
    this.customId = customId;
    return this;
  }

   /**
   * The API caller-provided external ID. Used to reconcile API caller-initiated transactions with PayPal transactions. Appears in transaction and settlement reports.
   * @return customId
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_CUSTOM_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getCustomId() {
    return customId;
  }


  @JsonProperty(JSON_PROPERTY_CUSTOM_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setCustomId(String customId) {
    this.customId = customId;
  }


  public AuthorizationAllOf networkTransactionReference(NetworkTransactionReference networkTransactionReference) {
    
    this.networkTransactionReference = networkTransactionReference;
    return this;
  }

   /**
   * Get networkTransactionReference
   * @return networkTransactionReference
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_NETWORK_TRANSACTION_REFERENCE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public NetworkTransactionReference getNetworkTransactionReference() {
    return networkTransactionReference;
  }


  @JsonProperty(JSON_PROPERTY_NETWORK_TRANSACTION_REFERENCE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setNetworkTransactionReference(NetworkTransactionReference networkTransactionReference) {
    this.networkTransactionReference = networkTransactionReference;
  }


  public AuthorizationAllOf sellerProtection(SellerProtection sellerProtection) {
    
    this.sellerProtection = sellerProtection;
    return this;
  }

   /**
   * Get sellerProtection
   * @return sellerProtection
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_SELLER_PROTECTION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public SellerProtection getSellerProtection() {
    return sellerProtection;
  }


  @JsonProperty(JSON_PROPERTY_SELLER_PROTECTION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setSellerProtection(SellerProtection sellerProtection) {
    this.sellerProtection = sellerProtection;
  }


  public AuthorizationAllOf expirationTime(String expirationTime) {
    
    this.expirationTime = expirationTime;
    return this;
  }

   /**
   * The date and time, in [Internet date and time format](https://tools.ietf.org/html/rfc3339#section-5.6). Seconds are required while fractional seconds are optional.&lt;blockquote&gt;&lt;strong&gt;Note:&lt;/strong&gt; The regular expression provides guidance but does not reject all invalid dates.&lt;/blockquote&gt;
   * @return expirationTime
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_EXPIRATION_TIME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getExpirationTime() {
    return expirationTime;
  }


  @JsonProperty(JSON_PROPERTY_EXPIRATION_TIME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setExpirationTime(String expirationTime) {
    this.expirationTime = expirationTime;
  }


   /**
   * An array of related [HATEOAS links](/docs/api/reference/api-responses/#hateoas-links).
   * @return links
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_LINKS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<LinkDescription> getLinks() {
    return links;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AuthorizationAllOf authorizationAllOf = (AuthorizationAllOf) o;
    return Objects.equals(this.id, authorizationAllOf.id) &&
        Objects.equals(this.amount, authorizationAllOf.amount) &&
        Objects.equals(this.invoiceId, authorizationAllOf.invoiceId) &&
        Objects.equals(this.customId, authorizationAllOf.customId) &&
        Objects.equals(this.networkTransactionReference, authorizationAllOf.networkTransactionReference) &&
        Objects.equals(this.sellerProtection, authorizationAllOf.sellerProtection) &&
        Objects.equals(this.expirationTime, authorizationAllOf.expirationTime) &&
        Objects.equals(this.links, authorizationAllOf.links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, amount, invoiceId, customId, networkTransactionReference, sellerProtection, expirationTime, links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AuthorizationAllOf {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    invoiceId: ").append(toIndentedString(invoiceId)).append("\n");
    sb.append("    customId: ").append(toIndentedString(customId)).append("\n");
    sb.append("    networkTransactionReference: ").append(toIndentedString(networkTransactionReference)).append("\n");
    sb.append("    sellerProtection: ").append(toIndentedString(sellerProtection)).append("\n");
    sb.append("    expirationTime: ").append(toIndentedString(expirationTime)).append("\n");
    sb.append("    links: ").append(toIndentedString(links)).append("\n");
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

    // add `id` to the URL query string
    if (getId() != null) {
      try {
        joiner.add(String.format("%sid%s=%s", prefix, suffix, URLEncoder.encode(String.valueOf(getId()), "UTF-8").replaceAll("\\+", "%20")));
      } catch (UnsupportedEncodingException e) {
        // Should never happen, UTF-8 is always supported
        throw new RuntimeException(e);
      }
    }

    // add `amount` to the URL query string
    if (getAmount() != null) {
      joiner.add(getAmount().toUrlQueryString(prefix + "amount" + suffix));
    }

    // add `invoice_id` to the URL query string
    if (getInvoiceId() != null) {
      try {
        joiner.add(String.format("%sinvoice_id%s=%s", prefix, suffix, URLEncoder.encode(String.valueOf(getInvoiceId()), "UTF-8").replaceAll("\\+", "%20")));
      } catch (UnsupportedEncodingException e) {
        // Should never happen, UTF-8 is always supported
        throw new RuntimeException(e);
      }
    }

    // add `custom_id` to the URL query string
    if (getCustomId() != null) {
      try {
        joiner.add(String.format("%scustom_id%s=%s", prefix, suffix, URLEncoder.encode(String.valueOf(getCustomId()), "UTF-8").replaceAll("\\+", "%20")));
      } catch (UnsupportedEncodingException e) {
        // Should never happen, UTF-8 is always supported
        throw new RuntimeException(e);
      }
    }

    // add `network_transaction_reference` to the URL query string
    if (getNetworkTransactionReference() != null) {
      joiner.add(getNetworkTransactionReference().toUrlQueryString(prefix + "network_transaction_reference" + suffix));
    }

    // add `seller_protection` to the URL query string
    if (getSellerProtection() != null) {
      joiner.add(getSellerProtection().toUrlQueryString(prefix + "seller_protection" + suffix));
    }

    // add `expiration_time` to the URL query string
    if (getExpirationTime() != null) {
      try {
        joiner.add(String.format("%sexpiration_time%s=%s", prefix, suffix, URLEncoder.encode(String.valueOf(getExpirationTime()), "UTF-8").replaceAll("\\+", "%20")));
      } catch (UnsupportedEncodingException e) {
        // Should never happen, UTF-8 is always supported
        throw new RuntimeException(e);
      }
    }

    // add `links` to the URL query string
    if (getLinks() != null) {
      for (int i = 0; i < getLinks().size(); i++) {
        if (getLinks().get(i) != null) {
          joiner.add(getLinks().get(i).toUrlQueryString(String.format("%slinks%s%s", prefix, suffix,
              "".equals(suffix) ? "" : String.format("%s%d%s", containerPrefix, i, containerSuffix))));
        }
      }
    }

    return joiner.toString();
  }

}

