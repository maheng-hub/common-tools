/*
 * Payments
 * <blockquote><strong>Deprecation notice:</strong> The <code>/v1/payments</code> endpoint is deprecated. Use the <code>/v2/payments</code> endpoint instead. For details, see <a href=\"/docs/checkout/integrate/\">PayPal Checkout Basic Integration</a>.</blockquote>Use the Payments REST API to easily and securely accept online and mobile payments. The payments name space contains resource collections for payments, sales, refunds, authorizations, captures, and orders.<blockquote><strong>Important:</strong> The use of the PayPal REST <code>/payments</code> APIs to accept credit card payments is restricted. Instead, you can accept credit card payments with <a href=\"https://www.braintreepayments.com/products/braintree-direct\">Braintree Direct</a>.</blockquote>You can enable customers to make PayPal and credit card payments with only a few clicks, depending on the country. You can accept an immediate payment or authorize a payment and capture it later. You can show details for completed payments, refunds, and authorizations. You can make full or partial refunds. You also can void or re-authorize authorizations. For more information, see the <a href=\"/docs/integration/direct/payments/\">Payments overview</a>.
 *
 * The version of the OpenAPI document: 1.12
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
 * The tokenized credit card details. You can use this instrument to fund a payment.
 */
@JsonPropertyOrder({
  CreditCardToken.JSON_PROPERTY_CREDIT_CARD_ID,
  CreditCardToken.JSON_PROPERTY_PAYER_ID,
  CreditCardToken.JSON_PROPERTY_EXTERNAL_CUSTOMER_ID,
  CreditCardToken.JSON_PROPERTY_LAST4,
  CreditCardToken.JSON_PROPERTY_TYPE,
  CreditCardToken.JSON_PROPERTY_EXPIRE_MONTH,
  CreditCardToken.JSON_PROPERTY_EXPIRE_YEAR
})
@JsonTypeName("credit_card_token")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-06-11T03:56:12.691299337Z[Atlantic/Reykjavik]")
public class CreditCardToken {
  public static final String JSON_PROPERTY_CREDIT_CARD_ID = "credit_card_id";
  private String creditCardId;

  public static final String JSON_PROPERTY_PAYER_ID = "payer_id";
  private String payerId;

  public static final String JSON_PROPERTY_EXTERNAL_CUSTOMER_ID = "external_customer_id";
  private String externalCustomerId;

  public static final String JSON_PROPERTY_LAST4 = "last4";
  private String last4;

  public static final String JSON_PROPERTY_TYPE = "type";
  private String type;

  public static final String JSON_PROPERTY_EXPIRE_MONTH = "expire_month";
  private Integer expireMonth;

  public static final String JSON_PROPERTY_EXPIRE_YEAR = "expire_year";
  private String expireYear;

  public CreditCardToken() {
  }

  @JsonCreator
  public CreditCardToken(
    @JsonProperty(JSON_PROPERTY_LAST4) String last4, 
    @JsonProperty(JSON_PROPERTY_TYPE) String type, 
    @JsonProperty(JSON_PROPERTY_EXPIRE_MONTH) Integer expireMonth, 
    @JsonProperty(JSON_PROPERTY_EXPIRE_YEAR) String expireYear
  ) {
    this();
    this.last4 = last4;
    this.type = type;
    this.expireMonth = expireMonth;
    this.expireYear = expireYear;
  }

  public CreditCardToken creditCardId(String creditCardId) {
    
    this.creditCardId = creditCardId;
    return this;
  }

   /**
   * The ID of credit card that is stored in the PayPal vault.
   * @return creditCardId
  **/
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_CREDIT_CARD_ID)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getCreditCardId() {
    return creditCardId;
  }


  @JsonProperty(JSON_PROPERTY_CREDIT_CARD_ID)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setCreditCardId(String creditCardId) {
    this.creditCardId = creditCardId;
  }


  public CreditCardToken payerId(String payerId) {
    
    this.payerId = payerId;
    return this;
  }

   /**
   * Deprecated. A unique ID that you can assign and track when you store a credit card in the vault or use a vaulted credit card. This ID can help to avoid unintentional use or misuse of credit cards and can be any value, such as a UUID, user name, or email address. **Required** when you use a vaulted credit card and if a &#x60;payer_id&#x60; was originally provided when you vaulted the credit card. Use external_customer_id instead.
   * @return payerId
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_PAYER_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getPayerId() {
    return payerId;
  }


  @JsonProperty(JSON_PROPERTY_PAYER_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setPayerId(String payerId) {
    this.payerId = payerId;
  }


  public CreditCardToken externalCustomerId(String externalCustomerId) {
    
    this.externalCustomerId = externalCustomerId;
    return this;
  }

   /**
   * The externally-provided ID of the customer.
   * @return externalCustomerId
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_EXTERNAL_CUSTOMER_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getExternalCustomerId() {
    return externalCustomerId;
  }


  @JsonProperty(JSON_PROPERTY_EXTERNAL_CUSTOMER_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setExternalCustomerId(String externalCustomerId) {
    this.externalCustomerId = externalCustomerId;
  }


   /**
   * The last four digits of the stored credit card number.
   * @return last4
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_LAST4)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getLast4() {
    return last4;
  }




   /**
   * The credit card type. Value is &#x60;visa&#x60;, &#x60;mastercard&#x60;, &#x60;discover&#x60;, or &#x60;amex&#x60;. Do not use these lowercase values for display.
   * @return type
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_TYPE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getType() {
    return type;
  }




   /**
   * The expiration month with no leading zero. Value is from &#x60;1&#x60; to &#x60;12&#x60;.
   * minimum: 1
   * maximum: 12
   * @return expireMonth
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_EXPIRE_MONTH)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Integer getExpireMonth() {
    return expireMonth;
  }




   /**
   * The four-digit expiration year.
   * @return expireYear
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_EXPIRE_YEAR)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getExpireYear() {
    return expireYear;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreditCardToken creditCardToken = (CreditCardToken) o;
    return Objects.equals(this.creditCardId, creditCardToken.creditCardId) &&
        Objects.equals(this.payerId, creditCardToken.payerId) &&
        Objects.equals(this.externalCustomerId, creditCardToken.externalCustomerId) &&
        Objects.equals(this.last4, creditCardToken.last4) &&
        Objects.equals(this.type, creditCardToken.type) &&
        Objects.equals(this.expireMonth, creditCardToken.expireMonth) &&
        Objects.equals(this.expireYear, creditCardToken.expireYear);
  }

  @Override
  public int hashCode() {
    return Objects.hash(creditCardId, payerId, externalCustomerId, last4, type, expireMonth, expireYear);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreditCardToken {\n");
    sb.append("    creditCardId: ").append(toIndentedString(creditCardId)).append("\n");
    sb.append("    payerId: ").append(toIndentedString(payerId)).append("\n");
    sb.append("    externalCustomerId: ").append(toIndentedString(externalCustomerId)).append("\n");
    sb.append("    last4: ").append(toIndentedString(last4)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    expireMonth: ").append(toIndentedString(expireMonth)).append("\n");
    sb.append("    expireYear: ").append(toIndentedString(expireYear)).append("\n");
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

    // add `credit_card_id` to the URL query string
    if (getCreditCardId() != null) {
      try {
        joiner.add(String.format("%scredit_card_id%s=%s", prefix, suffix, URLEncoder.encode(String.valueOf(getCreditCardId()), "UTF-8").replaceAll("\\+", "%20")));
      } catch (UnsupportedEncodingException e) {
        // Should never happen, UTF-8 is always supported
        throw new RuntimeException(e);
      }
    }

    // add `payer_id` to the URL query string
    if (getPayerId() != null) {
      try {
        joiner.add(String.format("%spayer_id%s=%s", prefix, suffix, URLEncoder.encode(String.valueOf(getPayerId()), "UTF-8").replaceAll("\\+", "%20")));
      } catch (UnsupportedEncodingException e) {
        // Should never happen, UTF-8 is always supported
        throw new RuntimeException(e);
      }
    }

    // add `external_customer_id` to the URL query string
    if (getExternalCustomerId() != null) {
      try {
        joiner.add(String.format("%sexternal_customer_id%s=%s", prefix, suffix, URLEncoder.encode(String.valueOf(getExternalCustomerId()), "UTF-8").replaceAll("\\+", "%20")));
      } catch (UnsupportedEncodingException e) {
        // Should never happen, UTF-8 is always supported
        throw new RuntimeException(e);
      }
    }

    // add `last4` to the URL query string
    if (getLast4() != null) {
      try {
        joiner.add(String.format("%slast4%s=%s", prefix, suffix, URLEncoder.encode(String.valueOf(getLast4()), "UTF-8").replaceAll("\\+", "%20")));
      } catch (UnsupportedEncodingException e) {
        // Should never happen, UTF-8 is always supported
        throw new RuntimeException(e);
      }
    }

    // add `type` to the URL query string
    if (getType() != null) {
      try {
        joiner.add(String.format("%stype%s=%s", prefix, suffix, URLEncoder.encode(String.valueOf(getType()), "UTF-8").replaceAll("\\+", "%20")));
      } catch (UnsupportedEncodingException e) {
        // Should never happen, UTF-8 is always supported
        throw new RuntimeException(e);
      }
    }

    // add `expire_month` to the URL query string
    if (getExpireMonth() != null) {
      try {
        joiner.add(String.format("%sexpire_month%s=%s", prefix, suffix, URLEncoder.encode(String.valueOf(getExpireMonth()), "UTF-8").replaceAll("\\+", "%20")));
      } catch (UnsupportedEncodingException e) {
        // Should never happen, UTF-8 is always supported
        throw new RuntimeException(e);
      }
    }

    // add `expire_year` to the URL query string
    if (getExpireYear() != null) {
      try {
        joiner.add(String.format("%sexpire_year%s=%s", prefix, suffix, URLEncoder.encode(String.valueOf(getExpireYear()), "UTF-8").replaceAll("\\+", "%20")));
      } catch (UnsupportedEncodingException e) {
        // Should never happen, UTF-8 is always supported
        throw new RuntimeException(e);
      }
    }

    return joiner.toString();
  }

}

