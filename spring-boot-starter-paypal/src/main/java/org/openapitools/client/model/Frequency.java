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
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.StringJoiner;

/**
 * The frequency of the billing cycle.
 */
@JsonPropertyOrder({
  Frequency.JSON_PROPERTY_INTERVAL_UNIT,
  Frequency.JSON_PROPERTY_INTERVAL_COUNT
})
@JsonTypeName("frequency")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-05-08T11:30:41.643502082Z[Atlantic/Reykjavik]")
public class Frequency {
  /**
   * The interval at which the subscription is charged or billed.
   */
  public enum IntervalUnitEnum {
    DAY("DAY"),
    
    WEEK("WEEK"),
    
    MONTH("MONTH"),
    
    YEAR("YEAR");

    private String value;

    IntervalUnitEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static IntervalUnitEnum fromValue(String value) {
      for (IntervalUnitEnum b : IntervalUnitEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  public static final String JSON_PROPERTY_INTERVAL_UNIT = "interval_unit";
  private IntervalUnitEnum intervalUnit;

  public static final String JSON_PROPERTY_INTERVAL_COUNT = "interval_count";
  private Integer intervalCount = 1;

  public Frequency() {
  }

  public Frequency intervalUnit(IntervalUnitEnum intervalUnit) {
    
    this.intervalUnit = intervalUnit;
    return this;
  }

   /**
   * The interval at which the subscription is charged or billed.
   * @return intervalUnit
  **/
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_INTERVAL_UNIT)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public IntervalUnitEnum getIntervalUnit() {
    return intervalUnit;
  }


  @JsonProperty(JSON_PROPERTY_INTERVAL_UNIT)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setIntervalUnit(IntervalUnitEnum intervalUnit) {
    this.intervalUnit = intervalUnit;
  }


  public Frequency intervalCount(Integer intervalCount) {
    
    this.intervalCount = intervalCount;
    return this;
  }

   /**
   * The number of intervals after which a subscriber is billed. For example, if the &#x60;interval_unit&#x60; is &#x60;DAY&#x60; with an &#x60;interval_count&#x60; of  &#x60;2&#x60;, the subscription is billed once every two days. The following table lists the maximum allowed values for the &#x60;interval_count&#x60; for each &#x60;interval_unit&#x60;:&lt;table&gt;&lt;thead&gt;&lt;tr&gt;&lt;th&gt;&lt;code&gt;Interval unit&lt;/code&gt;&lt;/th&gt;&lt;th&gt;Maximum interval count&lt;/th&gt;&lt;/tr&gt;&lt;/thead&gt;&lt;tbody&gt;&lt;tr&gt;&lt;td&gt;&lt;code&gt;DAY&lt;/code&gt;&lt;/td&gt;&lt;td align&#x3D;\&quot;right\&quot;&gt;365&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;&lt;code&gt;WEEK&lt;/code&gt;&lt;/td&gt;&lt;td align&#x3D;\&quot;right\&quot;&gt;52&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;&lt;code&gt;MONTH&lt;/code&gt;&lt;/td&gt;&lt;td align&#x3D;\&quot;right\&quot;&gt;12&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;&lt;code&gt;YEAR&lt;/code&gt;&lt;/td&gt;&lt;td align&#x3D;\&quot;right\&quot;&gt;1&lt;/td&gt;&lt;/tr&gt;&lt;/tbody&gt;&lt;/table&gt;
   * minimum: 1
   * maximum: 365
   * @return intervalCount
  **/
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_INTERVAL_COUNT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Integer getIntervalCount() {
    return intervalCount;
  }


  @JsonProperty(JSON_PROPERTY_INTERVAL_COUNT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setIntervalCount(Integer intervalCount) {
    this.intervalCount = intervalCount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Frequency frequency = (Frequency) o;
    return Objects.equals(this.intervalUnit, frequency.intervalUnit) &&
        Objects.equals(this.intervalCount, frequency.intervalCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(intervalUnit, intervalCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Frequency {\n");
    sb.append("    intervalUnit: ").append(toIndentedString(intervalUnit)).append("\n");
    sb.append("    intervalCount: ").append(toIndentedString(intervalCount)).append("\n");
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

    // add `interval_unit` to the URL query string
    if (getIntervalUnit() != null) {
      try {
        joiner.add(String.format("%sinterval_unit%s=%s", prefix, suffix, URLEncoder.encode(String.valueOf(getIntervalUnit()), "UTF-8").replaceAll("\\+", "%20")));
      } catch (UnsupportedEncodingException e) {
        // Should never happen, UTF-8 is always supported
        throw new RuntimeException(e);
      }
    }

    // add `interval_count` to the URL query string
    if (getIntervalCount() != null) {
      try {
        joiner.add(String.format("%sinterval_count%s=%s", prefix, suffix, URLEncoder.encode(String.valueOf(getIntervalCount()), "UTF-8").replaceAll("\\+", "%20")));
      } catch (UnsupportedEncodingException e) {
        // Should never happen, UTF-8 is always supported
        throw new RuntimeException(e);
      }
    }

    return joiner.toString();
  }

}

