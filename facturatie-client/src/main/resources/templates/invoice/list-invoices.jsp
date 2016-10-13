<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
      xmlns:layout="http://www.ultraq.net.nz/web/thymeleaf/layout"
      layout:decorate="layout">
<head>
    <title>Declarations - Overview</title>
</head>
<body>
<section layout:fragment="header">
    <h1>
        Invoices
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-inbox"></i> Home</a></li>
        <li>Invoice</li>
        <li class="active">Overview</li>
    </ol>
</section>
<div layout:fragment="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Overview</h3>

                    <div class="box-tools">
                        <div class="input-group input-group-sm" style="width: 150px;">
                            <input type="text" name="table_search" class="form-control pull-right" placeholder="Search">

                            <div class="input-group-btn">
                                <button type="submit" class="btn btn-default"><i class="fa fa-search"></i></button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.box-header -->
                <div class="box-body table-responsive no-padding">
                    <table class="table table-hover">
                        <tbody>
                        <tr>
                            <th>ID</th>
                            <th>Customer</th>
                            <th>Date created</th>
                            <th>Date payed</th>
                            <th>State</th>
                            <th>Vat</th>
                            <th width="4%">&nbsp;</th>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td>John Doe</td>
                            <td>11-7-2014</td>
                            <td><span class="label label-success">12-10-2016</span></td>
                            <td><span class="label label-success">Payed</span></td>
                            <td>21%</td>
                            <td>
                                <div class="btn-group">
                                    <a href="/Invoice/pay" class="btn btn-xs btn-primary"><span
                                            class="fa fa-pencil">Pay</span></a>
                                    <a href="/Invoice/delete/1" class="btn btn-xs btn-danger"><span
                                            class="fa fa-trash"></span></a>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>Pieter jansen</td>
                            <td>11-10-2015</td>
                            <td><span class="label label-success"></span></td>
                            <td><span class="label label-warning">Waiting for payment</span></td>
                            <td>21%</td>
                            <td>
                                <div class="btn-group">
                                    <a href="/Invoice/pay" class="btn btn-xs btn-primary"><span
                                            class="fa fa-pencil">Pay</span></a>
                                    <a href="/Invoice/delete/2" class="btn btn-xs btn-danger"><span
                                            class="fa fa-trash"></span></a>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>4</td>
                            <td>Hans jansen</td>
                            <td>11-10-2016</td>
                            <td><span class="label label-success"></span></td>
                            <td><span class="label label-error">Overdue</span></td>
                            <td>21%</td>
                            <td>
                                <div class="btn-group">
                                    <a href="/Invoice/pay" class="btn btn-xs btn-primary"><span
                                            class="fa fa-pencil">Pay</span></a>
                                    <a href="/Invoice/delete/3" class="btn btn-xs btn-danger"><span
                                            class="fa fa-trash"></span></a>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>4</td>
                            <td>Jan jansen</td>
                            <td>11-10-2015</td>
                            <td><span class="label label-success">12-10-2016</span></td>
                            <td><span class="label label-success">Payed</span></td>
                            <td>21%</td>
                            <td>
                                <div class="btn-group">
                                    <a href="/Invoice/pay" class="btn btn-xs btn-primary"><span
                                            class="fa fa-pencil">Pay</span></a>
                                    <a href="/Invoice/delete/4" class="btn btn-xs btn-danger"><span
                                            class="fa fa-trash"></span></a>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->
        </div>
    </div>
</div>
</body>
</html>