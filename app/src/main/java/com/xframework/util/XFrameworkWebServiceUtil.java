package com.xframework.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xframework.model.WS.BaseWSIn;
import com.xframework.model.WS.BaseWSOut;
import com.xframework.model.WS.CheckAllocationCheckBarcodeIn;
import com.xframework.model.WS.CheckAllocationCheckBarcodeOut;
import com.xframework.model.WS.CheckBarcodeInfoIn;
import com.xframework.model.WS.CheckBarcodeInfoOut;
import com.xframework.model.WS.CheckBatchOrderBarcodeIn;
import com.xframework.model.WS.CheckBatchOrderBarcodeOut;
import com.xframework.model.WS.CheckDeliveryBarcodeIn;
import com.xframework.model.WS.CheckDeliveryBarcodeOut;
import com.xframework.model.WS.CheckFrameBarcodeIn;
import com.xframework.model.WS.CheckFrameBarcodeOut;
import com.xframework.model.WS.CheckMoveBarcodeIn;
import com.xframework.model.WS.CheckMoveBarcodeOut;
import com.xframework.model.WS.CheckOrderReceivedBarcodeIn;
import com.xframework.model.WS.CheckOrderReceivedBarcodeOut;
import com.xframework.model.WS.CheckReceivedBarcodeIn;
import com.xframework.model.WS.CheckReceivedBarcodeOut;
import com.xframework.model.WS.CheckRequestBarcodeIn;
import com.xframework.model.WS.CheckRequestBarcodeOut;
import com.xframework.model.WS.CheckReturnBarcodeIn;
import com.xframework.model.WS.CheckReturnBarcodeOut;
import com.xframework.model.WS.CheckSpareBarcodeIn;
import com.xframework.model.WS.CheckSpareBarcodeOut;
import com.xframework.model.WS.DeleteAllocationCheckDetailIn;
import com.xframework.model.WS.DeleteAllocationCheckDetailOut;
import com.xframework.model.WS.DeliveryBatchAttIn;
import com.xframework.model.WS.DeliveryBatchAttOut;
import com.xframework.model.WS.DeliveryBatchBodyIn;
import com.xframework.model.WS.DeliveryBatchBodyOut;
import com.xframework.model.WS.GetAllocationInfoIn;
import com.xframework.model.WS.GetAllocationInfoOut;
import com.xframework.model.WS.GetBarcodeInfoIn;
import com.xframework.model.WS.GetBarcodeInfoOut;
import com.xframework.model.WS.GetBatchOrderInfoIn;
import com.xframework.model.WS.GetBatchOrderInfoOut;
import com.xframework.model.WS.GetBeltlineListIn;
import com.xframework.model.WS.GetBeltlineListOut;
import com.xframework.model.WS.GetCheckAllocationListIn;
import com.xframework.model.WS.GetCheckAllocationListOut;
import com.xframework.model.WS.GetCheckOrderListIn;
import com.xframework.model.WS.GetCheckOrderListOut;
import com.xframework.model.WS.GetDeliveryBatchAttListIn;
import com.xframework.model.WS.GetDeliveryBatchAttListOut;
import com.xframework.model.WS.GetDeliveryBatchBodyListIn;
import com.xframework.model.WS.GetDeliveryBatchBodyListOut;
import com.xframework.model.WS.GetGenerateAllDataIn;
import com.xframework.model.WS.GetGenerateAllDataOut;
import com.xframework.model.WS.GetModulesIn;
import com.xframework.model.WS.GetModulesOut;
import com.xframework.model.WS.GetOrderCodeIn;
import com.xframework.model.WS.GetOrderCodeOut;
import com.xframework.model.WS.GetProductInfoByCodeIn;
import com.xframework.model.WS.GetProductInfoByCodeOut;
import com.xframework.model.WS.GetProductInfoIn;
import com.xframework.model.WS.GetProductInfoOut;
import com.xframework.model.WS.GetRepairmansOut;
import com.xframework.model.WS.GetSpareOrderDetailsIn;
import com.xframework.model.WS.GetSpareOrderDetailsOut;
import com.xframework.model.WS.GetSpareRequestOrdersOut;
import com.xframework.model.WS.GetSpareReturnOrdersOut;
import com.xframework.model.WS.GetSuspendedBatchAttListIn;
import com.xframework.model.WS.GetSuspendedBatchAttListOut;
import com.xframework.model.WS.GetSuspendedBatchBodyListIn;
import com.xframework.model.WS.GetSuspendedBatchBodyListOut;
import com.xframework.model.WS.GetUserInfoIn;
import com.xframework.model.WS.GetUserInfoOut;
import com.xframework.model.WS.GetWorksListOut;
import com.xframework.model.WS.LoginIn;
import com.xframework.model.WS.LoginOut;
import com.xframework.model.WS.SaveAllocationCheckDetailIn;
import com.xframework.model.WS.SaveAllocationCheckDetailOut;
import com.xframework.model.WS.SaveDeliveryOrderIn;
import com.xframework.model.WS.SaveDeliveryOrderOut;
import com.xframework.model.WS.SaveLotTrackingDeliveryIn;
import com.xframework.model.WS.SaveMoveOrderIn;
import com.xframework.model.WS.SaveMoveOrderOut;
import com.xframework.model.WS.SaveReceivedOrderIn;
import com.xframework.model.WS.SaveReceivedOrderOut;
import com.xframework.model.WS.SaveRequestOrderIn;
import com.xframework.model.WS.SaveRequestOrderOut;
import com.xframework.model.WS.SaveReturnOrderIn;
import com.xframework.model.WS.SaveReturnOrderOut;
import com.xframework.model.WS.SaveSpareCheckIn;
import com.xframework.model.WS.SaveSpareMoveIn;
import com.xframework.model.WS.SaveSpareReceivedIn;
import com.xframework.model.WS.SaveSpareRequestIn;
import com.xframework.model.WS.SaveSpareReturnIn;
import com.xframework.model.WS.SaveSpareScrapIn;
import com.xframework.model.WS.SuspendedBatchAttIn;
import com.xframework.model.WS.SuspendedBatchAttOut;
import com.xframework.model.WS.SuspendedBatchBodyIn;
import com.xframework.model.WS.SuspendedBatchBodyOut;
import java.util.HashMap;

import static java.lang.String.format;

public class XFrameworkWebServiceUtil {
    private static final String FORMAT_ERROR = "{'status':120,'msg':'接口调用失败，错误原因:{0}{0}'}";

    private static final String UNKNOW_ERROR = "{'status':110,'msg':'接口调用失败，错误原因:{0}{0}'}";

    private static Gson gson = (new GsonBuilder()).setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

    public static CheckAllocationCheckBarcodeOut API_CheckAllocationCheckBarcode(CheckAllocationCheckBarcodeIn in) {
        String method_name = "API_CheckAllocationCheckBarcode";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, CheckAllocationCheckBarcodeOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), CheckAllocationCheckBarcodeOut.class);
        }
    }

    public static CheckBarcodeInfoOut API_CheckBarcodeInfo(CheckBarcodeInfoIn in) {
        String method_name = "API_CheckBarcodeInfo";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, CheckBarcodeInfoOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), CheckBarcodeInfoOut.class);
        }
    }

    public static CheckBatchOrderBarcodeOut API_CheckBatchOrderBarcode(CheckBatchOrderBarcodeIn in) {
        String method_name = "API_CheckBatchOrderBarcode";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, CheckBatchOrderBarcodeOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), CheckBatchOrderBarcodeOut.class);
        }
    }

    public static CheckDeliveryBarcodeOut API_CheckDeliveryBarcode(CheckDeliveryBarcodeIn in) {
        String method_name = "API_CheckDeliveryBarcode";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, CheckDeliveryBarcodeOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), CheckDeliveryBarcodeOut.class);
        }
    }

    public static CheckFrameBarcodeOut API_CheckFrameBarcode(CheckFrameBarcodeIn in) {
        String method_name = "API_CheckFrameBarcode";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, CheckFrameBarcodeOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), CheckFrameBarcodeOut.class);
        }
    }

    public static CheckMoveBarcodeOut API_CheckMoveBarcode(CheckMoveBarcodeIn in) {
        String method_name = "API_CheckMoveBarcode";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, CheckMoveBarcodeOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), CheckMoveBarcodeOut.class);
        }
    }

    public static CheckReceivedBarcodeOut API_CheckReceivedBarcode(CheckReceivedBarcodeIn in) {
        String method_name = "API_CheckReceivedBarcode";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, CheckReceivedBarcodeOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), CheckReceivedBarcodeOut.class);
        }
    }

    public static CheckOrderReceivedBarcodeOut API_CheckReceivedOrderBarcode(CheckOrderReceivedBarcodeIn in) {
        String method_name = "API_CheckReceivedOrderBarcode";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, CheckOrderReceivedBarcodeOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), CheckOrderReceivedBarcodeOut.class);
        }
    }

    public static CheckRequestBarcodeOut API_CheckRequestBarcode(CheckRequestBarcodeIn in) {
        String method_name = "API_CheckRequestBarcode";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, CheckRequestBarcodeOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), CheckRequestBarcodeOut.class);
        }
    }

    public static CheckReturnBarcodeOut API_CheckReturnBarcode(CheckReturnBarcodeIn in) {
        String method_name = "API_CheckReturnBarcode";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, CheckReturnBarcodeOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), CheckReturnBarcodeOut.class);
        }
    }

    public static CheckSpareBarcodeOut API_CheckSpareBarcode(CheckSpareBarcodeIn in) {
        String method_name = "API_CheckSpareBarcode";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, CheckSpareBarcodeOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), CheckSpareBarcodeOut.class);
        }
    }

    public static CheckSpareBarcodeOut API_CheckSpareReceivedBarcode(CheckSpareBarcodeIn in) {
        String method_name = "API_CheckSpareReceivedBarcode";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, CheckSpareBarcodeOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), CheckSpareBarcodeOut.class);
        }
    }

    public static DeleteAllocationCheckDetailOut API_DeleteAllocationCheckDetail(DeleteAllocationCheckDetailIn in) {
        String method_name = "API_DeleteAllocationCheckDetail";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, DeleteAllocationCheckDetailOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), DeleteAllocationCheckDetailOut.class);
        }
    }

    public static DeliveryBatchAttOut API_DeliveryBatchAtt(DeliveryBatchAttIn in) {
        String method_name = "API_DeliveryBatchAtt";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, DeliveryBatchAttOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), DeliveryBatchAttOut.class);
        }
    }

    public static DeliveryBatchBodyOut API_DeliveryBatchBody(DeliveryBatchBodyIn in) {
        String method_name = "API_DeliveryBatchBody";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, DeliveryBatchBodyOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), DeliveryBatchBodyOut.class);
        }
    }

    public static GetAllocationInfoOut API_GetAllocationInfo(GetAllocationInfoIn in) {
        String method_name = "API_GetAllocationInfo";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, GetAllocationInfoOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), GetAllocationInfoOut.class);
        }
    }

    public static GetBarcodeInfoOut API_GetBarcodeInfo(GetBarcodeInfoIn in) {
        String method_name = "API_GetBarcodeInfo";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, GetBarcodeInfoOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), GetBarcodeInfoOut.class);
        }
    }

    public static GetBatchOrderInfoOut API_GetBatchOrderInfo(GetBatchOrderInfoIn in) {
        String method_name = "API_GetBatchOrderInfo";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, GetBatchOrderInfoOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), GetBatchOrderInfoOut.class);
        }
    }

    public static GetCheckAllocationListOut API_GetCheckAllocationList(GetCheckAllocationListIn in) {
        String method_name = "API_GetCheckAllocationList";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, GetCheckAllocationListOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), GetCheckAllocationListOut.class);
        }
    }

    public static GetCheckOrderListOut API_GetCheckOrderList(GetCheckOrderListIn in) {
        String method_name = "API_GetCheckOrderList";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, GetCheckOrderListOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), GetCheckOrderListOut.class);
        }
    }

    public static GetDeliveryBatchAttListOut API_GetDeliveryBatchAttList(GetDeliveryBatchAttListIn in) {
        String method_name = "API_GetDeliveryBatchAttList";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, GetDeliveryBatchAttListOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), GetDeliveryBatchAttListOut.class);
        }
    }

    public static GetDeliveryBatchBodyListOut API_GetDeliveryBatchBodyList(GetDeliveryBatchBodyListIn in) {
        String method_name = "API_GetDeliveryBatchBodyList";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, GetDeliveryBatchBodyListOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), GetDeliveryBatchBodyListOut.class);
        }
    }

    public static GetGenerateAllDataOut API_GetGenerateAllData(GetGenerateAllDataIn in) {
        String method_name = "API_GetGenerateAllData";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, GetGenerateAllDataOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), GetGenerateAllDataOut.class);
        }
    }

    public static GetModulesOut API_GetModules(GetModulesIn in) {
        String method_name = "API_GetModules";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, GetModulesOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), GetModulesOut.class);
        }
    }

    public static GetOrderCodeOut API_GetOrderCode(GetOrderCodeIn in) {
        String method_name = "API_GetOrderCode";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, GetOrderCodeOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), GetOrderCodeOut.class);
        }
    }

    public static GetProductInfoOut API_GetProductInfo(GetProductInfoIn in) {
        String method_name = "API_GetProductInfo";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, GetProductInfoOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), GetProductInfoOut.class);
        }
    }

    public static GetProductInfoByCodeOut API_GetProductInfoByCode(GetProductInfoByCodeIn in) {
        String method_name = "API_GetProductInfoByCode";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, GetProductInfoByCodeOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), GetProductInfoByCodeOut.class);
        }
    }

    public static GetRepairmansOut API_GetRepairmans(BaseWSIn in) {
        String method_name = "API_GetRepairmans";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, GetRepairmansOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), GetRepairmansOut.class);
        }
    }

    public static GetOrderCodeOut API_GetSpareOrderCode(GetOrderCodeIn in) {
        String method_name = "API_GetSpareOrderCode";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, GetOrderCodeOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), GetOrderCodeOut.class);
        }
    }

    public static GetSpareOrderDetailsOut API_GetSpareRequestDetails(GetSpareOrderDetailsIn in) {
        String method_name = "API_GetSpareRequestDetails";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, GetSpareOrderDetailsOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), GetSpareOrderDetailsOut.class);
        }
    }

    public static GetSpareRequestOrdersOut API_GetSpareRequestOrders(BaseWSIn in) {
        String method_name = "API_GetSpareRequestOrders";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, GetSpareRequestOrdersOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), GetSpareRequestOrdersOut.class);
        }
    }

    public static GetSpareOrderDetailsOut API_GetSpareReturnDetails(GetSpareOrderDetailsIn in) {
        String method_name = "API_GetSpareReturnDetails";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, GetSpareOrderDetailsOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), GetSpareOrderDetailsOut.class);
        }
    }

    public static GetSpareReturnOrdersOut API_GetSpareReturnOrders(BaseWSIn in) {
        String method_name = "API_GetSpareReturnOrders";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, GetSpareReturnOrdersOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), GetSpareReturnOrdersOut.class);
        }
    }

    public static GetSuspendedBatchAttListOut API_GetSuspendedBatchAttList(GetSuspendedBatchAttListIn in) {
        String method_name = "API_GetSuspendedBatchAttList";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, GetSuspendedBatchAttListOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), GetSuspendedBatchAttListOut.class);
        }
    }

    public static GetSuspendedBatchBodyListOut API_GetSuspendedBatchBodyList(GetSuspendedBatchBodyListIn in) {
        String method_name = "API_GetSuspendedBatchBodyList";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, GetSuspendedBatchBodyListOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), GetSuspendedBatchBodyListOut.class);
        }
    }

    public static GetUserInfoOut API_GetUserInfo(GetUserInfoIn in) {
        String method_name = "API_GetUserInfo";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, GetUserInfoOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), GetUserInfoOut.class);
        }
    }

    public static LoginOut API_Login(LoginIn in) {
        String method_name = "API_GetUserInfo";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, LoginOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), LoginOut.class);
        }
    }

    public static SaveAllocationCheckDetailOut API_SaveAllocationCheckDetail(SaveAllocationCheckDetailIn in) {
        String method_name = "API_SaveAllocationCheckDetail";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, SaveAllocationCheckDetailOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), SaveAllocationCheckDetailOut.class);
        }
    }

    public static SaveDeliveryOrderOut API_SaveDeliveryOrder(SaveDeliveryOrderIn in) {
        String method_name = "API_SaveDeliveryOrder";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, SaveDeliveryOrderOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), SaveDeliveryOrderOut.class);
        }
    }

    public static SaveMoveOrderOut API_SaveMoveOrder(SaveMoveOrderIn in) {
        String method_name = "API_SaveMoveOrder";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, SaveMoveOrderOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), SaveMoveOrderOut.class);
        }
    }

    public static SaveReceivedOrderOut API_SaveReceivedOrder(SaveReceivedOrderIn in) {
        String method_name = "API_SaveReceivedOrder";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, SaveReceivedOrderOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), SaveReceivedOrderOut.class);
        }
    }

    public static SaveRequestOrderOut API_SaveRequestOrder(SaveRequestOrderIn in) {
        String method_name = "API_SaveRequestOrder";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, SaveRequestOrderOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), SaveRequestOrderOut.class);
        }
    }

    public static SaveReturnOrderOut API_SaveReturnOrder(SaveReturnOrderIn in) {
        String method_name = "API_SaveReturnOrder";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, SaveReturnOrderOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), SaveReturnOrderOut.class);
        }
    }

    public static BaseWSOut API_SaveSpareCheckOrder(SaveSpareCheckIn in) {
        String method_name = "API_SaveSpareCheckOrder";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, BaseWSOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), BaseWSOut.class);
        }
    }

    public static BaseWSOut API_SaveSpareMove(SaveSpareMoveIn in) {
        String method_name = "API_SaveSpareMove";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, BaseWSOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), BaseWSOut.class);
        }
    }

    public static BaseWSOut API_SaveSpareReceivedOrder(SaveSpareReceivedIn in) {
        String method_name = "API_SaveSpareReceivedOrder";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, BaseWSOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), BaseWSOut.class);
        }
    }

    public static BaseWSOut API_SaveSpareRequestOrder(SaveSpareRequestIn in) {
        String method_name = "API_SaveSpareRequestOrder";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, BaseWSOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), BaseWSOut.class);
        }
    }

    public static BaseWSOut API_SaveSpareReturnOrder(SaveSpareReturnIn in) {
        String method_name = "API_SaveSpareReturnOrder";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, BaseWSOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), BaseWSOut.class);
        }
    }

    public static BaseWSOut API_SaveSpareScrap(SaveSpareScrapIn in) {
        String method_name = "API_SaveSpareScrap";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, BaseWSOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), BaseWSOut.class);
        }
    }

    public static SuspendedBatchAttOut API_SuspendedBatchAtt(SuspendedBatchAttIn in) {
        String method_name = "API_SuspendedBatchAtt";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, SuspendedBatchAttOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), SuspendedBatchAttOut.class);
        }
    }

    public static SuspendedBatchBodyOut API_SuspendedBatchBody(SuspendedBatchBodyIn in) {
        String method_name = "API_SuspendedBatchBody";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, SuspendedBatchBodyOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), SuspendedBatchBodyOut.class);
        }
    }

    public static GetWorksListOut API_GetWorksList(BaseWSIn in) {
        String method_name = "API_GetWorksList";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, GetWorksListOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), GetWorksListOut.class);
        }
    }

    public static BaseWSOut API_SaveLotTrackingDelivery(SaveLotTrackingDeliveryIn in) {
        String method_name = "API_SaveLotTrackingDelivery";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, BaseWSOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), BaseWSOut.class);
        }
    }

    public static GetBeltlineListOut API_GetBeltlineList(GetBeltlineListIn in) {
        String method_name = "API_GetBeltlineList";
        String result = sendSoapPost(method_name, in.toJson());
        try {
            return gson.fromJson(result, GetBeltlineListOut.class);
        } catch (Exception e) {
            return gson.fromJson(format(FORMAT_ERROR, e.getMessage()), GetBeltlineListOut.class);
        }
    }

    public static String sendSoapPost(String method_name, String strJson) {
        HashMap<String, String> params = new HashMap<>();
        params.put("strJson", strJson);
        try {
            String result = HttpUtil.sendSoapPost(method_name, params);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return format(UNKNOW_ERROR, e.getMessage());
        }
    }
}
