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

package org.openapitools.client.api;

import com.fasterxml.jackson.core.type.TypeReference;

import org.openapitools.client.ApiException;
import org.openapitools.client.ApiClient;
import org.openapitools.client.Configuration;
import org.openapitools.client.model.*;
import org.openapitools.client.Pair;

import org.openapitools.client.model.AuthorizationsGet403Response;
import org.openapitools.client.model.AuthorizationsGet404Response;
import org.openapitools.client.model.AuthorizationsVoid401Response;
import org.openapitools.client.model.ErrorDefault;
import org.openapitools.client.model.Refund;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-06-11T03:52:37.374153451Z[Atlantic/Reykjavik]")
public class RefundsApi {


  private ApiClient apiClient;

  public RefundsApi() {
    this(Configuration.getDefaultApiClient());
  }

  public RefundsApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Show refund details
   * Shows details for a refund, by ID.
   * @param refundId The PayPal-generated ID for the refund for which to show details. (required)
   * @return Refund
   * @throws ApiException if fails to make API call
   */
  public Refund refundsGet(String refundId) throws ApiException {
    return this.refundsGet(refundId, Collections.emptyMap());
  }


  /**
   * Show refund details
   * Shows details for a refund, by ID.
   * @param refundId The PayPal-generated ID for the refund for which to show details. (required)
   * @param additionalHeaders additionalHeaders for this call
   * @return Refund
   * @throws ApiException if fails to make API call
   */
  public Refund refundsGet(String refundId, Map<String, String> additionalHeaders) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'refundId' is set
    if (refundId == null) {
      throw new ApiException(400, "Missing the required parameter 'refundId' when calling refundsGet");
    }
    
    // create path and map variables
    String localVarPath = "/v2/payments/refunds/{refund_id}"
      .replaceAll("\\{" + "refund_id" + "\\}", apiClient.escapeString(refundId.toString()));

    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    
    localVarHeaderParams.putAll(additionalHeaders);

    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Oauth2" };

    TypeReference<Refund> localVarReturnType = new TypeReference<Refund>() {};
    return apiClient.invokeAPI(
        localVarPath,
        "GET",
        localVarQueryParams,
        localVarCollectionQueryParams,
        localVarQueryStringJoiner.toString(),
        localVarPostBody,
        localVarHeaderParams,
        localVarCookieParams,
        localVarFormParams,
        localVarAccept,
        localVarContentType,
        localVarAuthNames,
        localVarReturnType
    );
  }

}
