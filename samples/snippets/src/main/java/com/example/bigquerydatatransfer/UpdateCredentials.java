/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.bigquerydatatransfer;

// [START bigquerydatatransfer_update_credentials]
import com.google.api.gax.rpc.ApiException;
import com.google.cloud.bigquery.datatransfer.v1.DataTransferServiceClient;
import com.google.cloud.bigquery.datatransfer.v1.TransferConfig;
import com.google.cloud.bigquery.datatransfer.v1.UpdateTransferConfigRequest;
import com.google.protobuf.FieldMask;
import com.google.protobuf.util.FieldMaskUtil;
import java.io.IOException;

// Sample to update credentials in transfer config.
public class UpdateCredentials {

  public static void main(String[] args) throws IOException {
    // TODO(developer): Replace these variables before running the sample.
    String configId = "MY_CONFIG_ID";
    String serviceAccount = "MY_SERVICE_ACCOUNT";
    TransferConfig transferConfig = TransferConfig.newBuilder().setName(configId).build();
    FieldMask updateMask = FieldMaskUtil.fromString("service_account_name");
    updateCredentials(transferConfig, serviceAccount, updateMask);
  }

  public static void updateCredentials(
      TransferConfig transferConfig, String serviceAccount, FieldMask updateMask)
      throws IOException {
    try (DataTransferServiceClient dataTransferServiceClient = DataTransferServiceClient.create()) {
      UpdateTransferConfigRequest request =
          UpdateTransferConfigRequest.newBuilder()
              .setTransferConfig(transferConfig)
              .setUpdateMask(updateMask)
              .setServiceAccountName(serviceAccount)
              .build();
      dataTransferServiceClient.updateTransferConfig(request);
      System.out.println("Credentials updated successfully");
    } catch (ApiException ex) {
      System.out.print("Credentials was not updated." + ex.toString());
    }
  }
}
// [END bigquerydatatransfer_update_credentials]
