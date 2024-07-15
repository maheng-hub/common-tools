/*
 * Subscriptions
 * You can use billing plans and subscriptions to create subscriptions that process recurring PayPal payments for physical or digital goods, or services. A plan includes pricing and billing cycle information that defines the amount and frequency of charge for a subscription. You can also define a fixed plan, such as a $5 basic plan or a volume- or graduated-based plan with pricing tiers based on the quantity purchased. For more information, see <a href=\"/docs/subscriptions/\">Subscriptions Overview</a>.
 *
 * The version of the OpenAPI document: 1.6
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
import org.openapitools.client.model.BillingCycleOverride;
import org.openapitools.client.model.PaymentPreferencesOverride;
import org.openapitools.client.model.TaxesOverride;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.StringJoiner;

/**
 * An inline plan object to customise the subscription. You can override plan level default attributes by providing customised values for the subscription in this object.
 */
@JsonPropertyOrder({
  PlanOverride.JSON_PROPERTY_BILLING_CYCLES,
  PlanOverride.JSON_PROPERTY_PAYMENT_PREFERENCES,
  PlanOverride.JSON_PROPERTY_TAXES
})
@JsonTypeName("plan_override")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-05-08T11:30:41.643502082Z[Atlantic/Reykjavik]")
public class PlanOverride {
  public static final String JSON_PROPERTY_BILLING_CYCLES = "billing_cycles";
  private List<BillingCycleOverride> billingCycles;

  public static final String JSON_PROPERTY_PAYMENT_PREFERENCES = "payment_preferences";
  private PaymentPreferencesOverride paymentPreferences;

  public static final String JSON_PROPERTY_TAXES = "taxes";
  private TaxesOverride taxes;

  public PlanOverride() {
  }

  public PlanOverride billingCycles(List<BillingCycleOverride> billingCycles) {
    
    this.billingCycles = billingCycles;
    return this;
  }

  public PlanOverride addBillingCyclesItem(BillingCycleOverride billingCyclesItem) {
    if (this.billingCycles == null) {
      this.billingCycles = new ArrayList<>();
    }
    this.billingCycles.add(billingCyclesItem);
    return this;
  }

   /**
   * An array of billing cycles for trial billing and regular billing. The subscription billing cycle definition has to adhere to the plan billing cycle definition.
   * @return billingCycles
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_BILLING_CYCLES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<BillingCycleOverride> getBillingCycles() {
    return billingCycles;
  }


  @JsonProperty(JSON_PROPERTY_BILLING_CYCLES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setBillingCycles(List<BillingCycleOverride> billingCycles) {
    this.billingCycles = billingCycles;
  }


  public PlanOverride paymentPreferences(PaymentPreferencesOverride paymentPreferences) {
    
    this.paymentPreferences = paymentPreferences;
    return this;
  }

   /**
   * Get paymentPreferences
   * @return paymentPreferences
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_PAYMENT_PREFERENCES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public PaymentPreferencesOverride getPaymentPreferences() {
    return paymentPreferences;
  }


  @JsonProperty(JSON_PROPERTY_PAYMENT_PREFERENCES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setPaymentPreferences(PaymentPreferencesOverride paymentPreferences) {
    this.paymentPreferences = paymentPreferences;
  }


  public PlanOverride taxes(TaxesOverride taxes) {
    
    this.taxes = taxes;
    return this;
  }

   /**
   * Get taxes
   * @return taxes
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_TAXES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public TaxesOverride getTaxes() {
    return taxes;
  }


  @JsonProperty(JSON_PROPERTY_TAXES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setTaxes(TaxesOverride taxes) {
    this.taxes = taxes;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PlanOverride planOverride = (PlanOverride) o;
    return Objects.equals(this.billingCycles, planOverride.billingCycles) &&
        Objects.equals(this.paymentPreferences, planOverride.paymentPreferences) &&
        Objects.equals(this.taxes, planOverride.taxes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(billingCycles, paymentPreferences, taxes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PlanOverride {\n");
    sb.append("    billingCycles: ").append(toIndentedString(billingCycles)).append("\n");
    sb.append("    paymentPreferences: ").append(toIndentedString(paymentPreferences)).append("\n");
    sb.append("    taxes: ").append(toIndentedString(taxes)).append("\n");
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

    // add `billing_cycles` to the URL query string
    if (getBillingCycles() != null) {
      for (int i = 0; i < getBillingCycles().size(); i++) {
        if (getBillingCycles().get(i) != null) {
          joiner.add(getBillingCycles().get(i).toUrlQueryString(String.format("%sbilling_cycles%s%s", prefix, suffix,
              "".equals(suffix) ? "" : String.format("%s%d%s", containerPrefix, i, containerSuffix))));
        }
      }
    }

    // add `payment_preferences` to the URL query string
    if (getPaymentPreferences() != null) {
      joiner.add(getPaymentPreferences().toUrlQueryString(prefix + "payment_preferences" + suffix));
    }

    // add `taxes` to the URL query string
    if (getTaxes() != null) {
      joiner.add(getTaxes().toUrlQueryString(prefix + "taxes" + suffix));
    }

    return joiner.toString();
  }

}
