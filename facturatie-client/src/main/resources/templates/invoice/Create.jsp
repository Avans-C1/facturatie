<%--
  Created by IntelliJ IDEA.
  User: kevin
  Date: 11-10-2016
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
      xmlns:layout="http://www.ultraq.net.nz/web/thymeleaf/layout"
      layout:decorate="layout">
<head>
    <title>Declarations - Create</title>
</head>
<body>
<section layout:fragment="header">
    <h1>
        Declarations
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-inbox"></i> Home</a></li>
        <li>Declaration</li>
        <li class="active">Create</li>
    </ol>
</section>
<div layout:fragment="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">Create</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <!-- Form om een nieuwe declaration te maken. Het object 'declaration' is het object waarin de
                    waarden uit het formulier worden ingevuld. Het is afkomstig uit de DeclarationController. -->
                    <form action="#" th:action="@{/declaration/create}" th:object="${Invoice}" method="post">

                        <!-- Het formulier wordt na verzenden gevalideerd in de DeclarationController.
                            Als er fouten waren kun je hier een lijst met foutmeldingen tonen.
                            Je kunt ook de melding bij het inputveld tonen (zie code hieronder) en evt. de CSS class
                            van het inputveld veranderen. -->
                        <!--<ul th:if="${#fields.hasErrors('*')}" class="errorlist">-->
                        <!--<li th:each="err : ${#fields.errors('*')}" th:text="${err}">Input is incorrect</li>-->
                        <!--</ul>-->

                        <div th:if="${#fields.hasErrors('*')}">
                            <div layout:include="views/fragments/alert :: alert"
                                 th:with="type='danger', header='Fouten in het formulier'" th:remove="tag">
                                <!--/* Implements alert summary fragment with full-blown HTML summary */-->
                                <th:block layout:fragment="alert-summary">
                                    <p>Niet alle velden in het formulier zijn correct ingevuld. Corrigeer deze en
                                        verzend opnieuw.</p>
                                    <!--<p><button type="button" class="btn btn-danger">Take this action</button></p>-->
                                </th:block>
                            </div>
                        </div>
                        <div th:if="${exception}">
                            <div layout:include="views/fragments/alert :: alert"
                                 th:with="type='danger', header='Fouten in het formulier'" th:remove="tag">
                                <!--/* Implements alert summary fragment with full-blown HTML summary */-->
                                <th:block layout:fragment="alert-summary">
                                    <p th:text="${exception}">Exception message</p>
                                </th:block>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-sm-12">
                                <div class="form-group">
                                    <label for="customer">Customer</label>
                                    <!-- th:field="*{name}" is de koppeling naar het attribuut 'name' van het object 'member'-->
                                    <input type="text" class="form-control" id="customer" th:field="*{customer}"/>
                                    <p th:if="${#fields.hasErrors('customer')}" th:errors="*{customer}">Name Error</p>
                                </div>

                                <div class="form-group">
                                    <label for="threatment">Threatment</label>
                                    <!-- th:field="*{name}" is de koppeling naar het attribuut 'name' van het object 'member'-->
                                    <input type="text" class="form-control" id="threatment" th:field="*{threatment}"/>
                                    <p th:if="${#fields.hasErrors('threatment')}" th:errors="*{threatment}">Name
                                        Error</p>
                                </div>

                                <div class="form-group">
                                    <label for="invoice">Invoice</label>
                                    <!-- th:field="*{name}" is de koppeling naar het attribuut 'name' van het object 'member'-->
                                    <input type="text" class="form-control" id="invoice" th:field="*{invoice}"/>
                                    <p th:if="${#fields.hasErrors('invoice')}" th:errors="*{invoice}">Name Error</p>
                                </div>

                                <div class="form-group">
                                    <label for="declaredAt">Declared At</label>
                                    <!-- th:field="*{name}" is de koppeling naar het attribuut 'name' van het object 'member'-->
                                    <input type="date" class="form-control" id="declaredAt" th:field="*{declaredAt}"/>
                                    <p th:if="${#fields.hasErrors('declaredAt')}" th:errors="*{declaredAt}">Name
                                        Error</p>
                                </div>

                                <div class="form-group">
                                    <label for="price">Price</label>
                                    <!-- th:field="*{name}" is de koppeling naar het attribuut 'name' van het object 'member'-->
                                    <input type="text" class="form-control" id="price" th:field="*{price}"/>
                                    <p th:if="${#fields.hasErrors('price')}" th:errors="*{price}">Name Error</p>
                                </div>
                            </div>
                        </div>

                        <!-- De button die voor verzenden van het formulier zorgt.
                            De name 'save' is een parameter waarmee in de MemberController de juiste
                            save-actie wordt gestart. -->
                        <button type="submit" class="btn btn-primary" name="save">Submit</button>

                    </form>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->
        </div>
    </div>
</div>
</body>
</html>
