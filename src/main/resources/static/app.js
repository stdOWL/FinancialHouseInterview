$( document ).ready(function() {

    const fetchClientInfo = (transactionId) => {
        const token = localStorage.getItem("token");
        const request = {transactionId};
        $.ajax({
            type: "POST",
            url: "/auth/client",
            data: JSON.stringify(request),
            contentType: "application/json; charset=utf-8",
            headers:{
                "Authorization": token
            },
            dataType: "json",
            success: function(data){
                if(data.status === "OK"){
                    const customerInfo = data?.data?.customerInfo;
                    $("#clientInfoDetail").html(JSON.stringify(customerInfo));
                }
            },
            error: function(errMsg) {
                alert(errMsg);
            }
        });

    };
    const fetchTransactionInfo = (transactionId) => {
        const token = localStorage.getItem("token");
        const request = {transactionId};
        $.ajax({
            type: "POST",
            url: "/transactions/get",
            data: JSON.stringify(request),
            contentType: "application/json; charset=utf-8",
            headers:{
                "Authorization": token
            },
            dataType: "json",
            success: function(data){
                if(data.status === "OK"){
                    const txInfo = data?.data;
                    $("#txInfoDetail").html(JSON.stringify(txInfo));
                }
            },
            error: function(errMsg) {
                alert(errMsg);
            }
        });

    };
    const fetchTransactionQuery = (request) => {
        const token = localStorage.getItem("token");
        $.ajax({
            type: "POST",
            url: "/transactions/list",
            data: JSON.stringify(request),
            contentType: "application/json; charset=utf-8",
            headers:{
                "Authorization": token
            },
            dataType: "json",
            success: function(data){
                if(data.status === "OK"){
                    const info = data?.data;
                    $('#txQuery-table').bootstrapTable({
                        columns: [{
                            field: 'customerInfo',
                            title: 'Customer Info',
                            formatter: (value, row) => JSON.stringify(value)
                        }, {
                            field: 'merchant',
                            title: 'Merchant Info',
                            formatter: (value, row) => JSON.stringify(value)

                        }, {
                            field: 'fx',
                            title: 'FX Info',
                            formatter: (value, row) => JSON.stringify(value)

                        }, {
                            field: 'transaction',
                            title: 'TX Info',
                            formatter: (value, row) => JSON.stringify(value)

                        }],
                        data: info.data
                    })
                }
            },
            error: function(errMsg) {
                alert(errMsg);
            }
        });

    };
    const fetchTransactionReport = (request) => {
        const token = localStorage.getItem("token");
        $.ajax({
            type: "POST",
            url: "/transactions/report",
            data: JSON.stringify(request),
            contentType: "application/json; charset=utf-8",
            headers:{
                "Authorization": token
            },
            dataType: "json",
            success: function(data){
                if(data.status === "OK"){
                    const info = data?.data;
                    $('#txReport-table').bootstrapTable({
                        columns: [{
                            field: 'currency',
                            title: 'currency'
                        }, {
                            field: 'count',
                            title: 'count'
                        }, {
                            field: 'total',
                            title: 'total'
                        }],
                        data: info
                    })
                }
            },
            error: function(errMsg) {
                alert(errMsg);
            }
        });

    };

    $('#form-txReport-info').on('submit', function(e){
        e.preventDefault();
        const fromDate = $("#txReport-fromDate").val();
        const toDate = $("#txReport-toDate").val();


        fetchTransactionReport({fromDate,toDate});
    });

    $('#form-txQuery-info').on('submit', function(e){
        e.preventDefault();
        const fromDate = $("#txQuery-fromDate").val();
        const toDate = $("#txQuery-toDate").val();
        const merchantId = $("#txQuery-merchantId").val();
        const page = $("#txQuery-page").val();


        fetchTransactionQuery({fromDate,toDate,merchantId,page});
    });

    $('#form-transaction-info').on('submit', function(e){
        e.preventDefault();
        const transactionId = $("#client-transaction-tx-id").val();
        fetchTransactionInfo(transactionId);
    });

    $('#form-client-info').on('submit', function(e){
        e.preventDefault();
        const transactionId = $("#client-info-tx-id").val();
        fetchClientInfo(transactionId);
    });

    // login
    $('#form-signin').on('submit', function(e){
        e.preventDefault();
        const email = $("#username").val();
        const password = $("#password").val();
        const request = {email, password};

        $.post("/auth/login", {email, password}, function(result) {
            console(result);
        }, );

        $.ajax({
            type: "POST",
            url: "/auth/login",
            data: JSON.stringify(request),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(data){
                if(data.status === "OK"){
                    localStorage.setItem("token", data.data.token);
                    $("#login").hide();
                    $("#clientInfo").show();
                    $("#txQuery").show();
                    $("#txReport").show();
                    $("#txInfo").show();

                }
            },
            error: function(errMsg) {
                alert(errMsg);
            }
        });

    });




});
