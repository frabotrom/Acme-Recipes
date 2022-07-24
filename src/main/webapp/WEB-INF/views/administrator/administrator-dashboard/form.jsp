<%@page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<style>
.column {
	float: left;
	width: 25%;
}
.column-50 {
	float: right;
	width: 50%;
}
.row:after {
	content: "";
	display: table;
	clear: both;
}
</style>
<acme:form readonly="true">

			<h3>
				<acme:message code="administrator.administrator-dashboard.form.label.total-number-of-ingredients"/>
			</h3>
			<table>
			<caption></caption>
				<tr>
				<th id="totalNumberOfIngredientsId">
					<acme:print value="${totalNumberOfIngredients}"/>
				</th>
				</tr>
			</table>
			<h3>
				<acme:message code="administrator.administrator-dashboard.form.label.total-number-of-kitchen-utensils"/>
			</h3>
			<table>
				<caption></caption>
				<tr>
				<th id="totalNumberOfKitchenUtensilsId">
					<acme:print value="${totalNumberOfKitchenUtensils}"/>
				</th>
				</tr>
			</table>
			
			<h3>
				<acme:message
					code="administrator.administrator-dashboard.form.label.total-number-of-fine-dishes-by-status" />
			</h3>
			<table class="table table-sm">
			<caption></caption>
			<div class="column">
				<jstl:forEach items="${ totalNumberOfPFineDishesByStatus.keySet() }"
					var="key">
					<tr>
						<jstl:set value="${ totalNumberOfPFineDishesByStatus.get(key) }"
							var="amount" />
						<jstl:if test="${ amount>0 }">
							<th scope="row" style="width: 15%"><acme:message
									code="administrator.dashboard.form.status.${ key }" /></th>
							<td><acme:print value="${ amount }" /></td>
						</jstl:if>

					</tr>
				</jstl:forEach>
				</div>
				<div class="column-50">
				<canvas id="total-canvas"></canvas>
				</div>
			</table>
			<h3>
				<acme:message
					code="administrator.administrator-dashboard.form.label.average-retail-price-of-ingredients-by-currency" />
			</h3>

			<table class="table table-sm">
			<caption></caption>
			<div class="column">
				<jstl:forEach
					items="${ averageRetailPriceOfIngredientByCurrency.keySet() }"
					var="key">
					<tr>
						<jstl:set
							value="${ averageRetailPriceOfIngredientByCurrency.get(key) }"
							var="amount" />
						<jstl:if test="${ amount>0 }">
							<th scope="row" style="width: 15%"><acme:message
									code="administrator.dashboard.form.status.${ key.getFirst() }" /></th>
							<td><acme:print value="${ amount }" /></td>
						</jstl:if>

					</tr>
				</jstl:forEach>
				</div>
				<div class="column-50">
				<canvas id="average-canvas"></canvas>
				</div>
			</table>
			<h3>
				<acme:message
					code="administrator.administrator-dashboard.form.label.deviation-retail-price-of-ingredients-currency" />
			</h3>

			<table class="table table-sm">
			<caption></caption>
			<div class="column">
				<jstl:forEach
					items="${ deviationRetailPriceOfIngredientByCurrency.keySet() }"
					var="key">
					<tr>
						<jstl:set
							value="${ deviationRetailPriceOfIngredientByCurrency.get(key) }"
							var="amount" />
						<jstl:if test="${ amount>0 }">
							<th scope="row" style="width: 15%"><acme:message
									code="administrator.dashboard.form.status.${ key.getFirst() }" /></th>
							<td><acme:print value="${ amount }" /></td>
						</jstl:if>

					</tr>
				</jstl:forEach>
				</div>
				<div class="column-50">
				<canvas id="dev-canvas"></canvas>
				</div>
			</table>

			<h3>
				<acme:message
					code="administrator.administrator-dashboard.form.label.minimum-retail-price-of-ingredients-currency" />
			</h3>

			<table class="table table-sm">
			<caption></caption>
			<div class="column">
				<jstl:forEach items="${ minimumRetailPriceOfIngredientByCurrency.keySet() }"
					var="key">
					<tr>
						<jstl:set value="${ minimumRetailPriceOfIngredientByCurrency.get(key) }"
							var="amount" />
						<jstl:if test="${ amount>0 }">
							<th scope="row" style="width: 15%"><acme:message
									code="administrator.dashboard.form.status.${ key.getFirst() }" /></th>
							<td><acme:print value="${ amount }" /></td>
						</jstl:if>

					</tr>
				</jstl:forEach>
				</div>
				<div class="column-50">
				<canvas id="min-canvas"></canvas>
				</div>
			</table>

			<h3>
				<acme:message
					code="administrator.administrator-dashboard.form.label.maximum-retail-price-of-ingredients-currency" />
			</h3>
			<table class="table table-sm">
			<caption></caption>
			<div class="column">
				<jstl:forEach items="${ maximumRetailPriceOfIngredientByCurrency.keySet() }"
					var="key">
					<tr>
						<jstl:set value="${ maximumRetailPriceOfIngredientByCurrency.get(key) }"
							var="amount" />
						<jstl:if test="${ amount>0 }">
							<th scope="row" style="width: 15%"><acme:message
									code="administrator.dashboard.form.status.${ key.getFirst() }" /></th>
							<td><acme:print value="${ amount }" /></td>
						</jstl:if>

					</tr>
				</jstl:forEach>
				</div>
				<div class="column-50">
				<canvas id="max-canvas"></canvas>
				</div>
			</table>
			
			<h3>
				<acme:message
					code="administrator.administrator-dashboard.form.label.average-retail-price-of-kitchen-utensils-by-currency" />
			</h3>
			<table class="table table-sm">
			<caption></caption>
			<div class="column">
				<jstl:forEach items="${ averageRetailPriceOfKitchenUtensilsByCurrency.keySet() }"
					var="key">
					<tr>
						<jstl:set value="${ averageRetailPriceOfKitchenUtensilsByCurrency.get(key) }"
							var="amount" />
						<jstl:if test="${ amount>0 }">
							<th scope="row" style="width: 15%"><acme:message
									code="administrator.dashboard.form.status.${ key }" /></th>
							<td><acme:print value="${ amount }" /></td>
						</jstl:if>

					</tr>
				</jstl:forEach>
				</div>
				<div class="column-50">
				<canvas id="avg2-canvas"></canvas>
				</div>
			</table>
			
			<h3>
				<acme:message
					code="administrator.administrator-dashboard.form.label.deviation-retail-price-of-kitchen-utensils-by-currency" />
			</h3>
			<table class="table table-sm">
			<caption></caption>
				<div class="column">
				<jstl:forEach items="${ deviationRetailPriceOfKitchenUtensilsByCurrency.keySet() }"
					var="key">
					<tr>
						<jstl:set value="${ deviationRetailPriceOfKitchenUtensilsByCurrency.get(key) }"
							var="amount" />
						<jstl:if test="${ amount>0 }">
							<th scope="row" style="width: 15%"><acme:message
									code="administrator.dashboard.form.status.${ key }" /></th>
							<td><acme:print value="${ amount }" /></td>
						</jstl:if>

					</tr>
				</jstl:forEach>
				</div>
				<div class="column-50">
				<canvas id="dev2-canvas"></canvas>
				</div>
			</table>
			
			<h3>
				<acme:message
					code="administrator.administrator-dashboard.form.label.minimum-retail-price-of-kitchen-utensils-by-currency" />
			</h3>
			<table class="table table-sm">
			<caption></caption>
			<div class="column">
				<jstl:forEach items="${ minimumRetailPriceOfKitchenUtensilsByCurrency.keySet() }"
					var="key">
					<tr>
						<jstl:set value="${ minimumRetailPriceOfKitchenUtensilsByCurrency.get(key) }"
							var="amount" />
						<jstl:if test="${ amount>0 }">
							<th scope="row" style="width: 15%"><acme:message
									code="administrator.dashboard.form.status.${ key }" /></th>
							<td><acme:print value="${ amount }" /></td>
						</jstl:if>

					</tr>
				</jstl:forEach>
				</div>
				<div class="column-50">
				<canvas id="min2-canvas"></canvas>
				</div>
			</table>
			
			<h3>
				<acme:message
					code="administrator.administrator-dashboard.form.label.maximum-retail-price-of-kitchen-utensils-by-currency" />
			</h3>
			<table class="table table-sm">
			<caption></caption>
			<div class="column">
				<jstl:forEach items="${ maximumRetailPriceOfKitchenUtensilsByCurrency.keySet() }"
					var="key">
					<tr>
						<jstl:set value="${ maximumRetailPriceOfKitchenUtensilsByCurrency.get(key) }"
							var="amount" />
						<jstl:if test="${ amount>0 }">
							<th scope="row" style="width: 15%"><acme:message
									code="administrator.dashboard.form.status.${ key }" /></th>
							<td><acme:print value="${ amount }" /></td>
						</jstl:if>

					</tr>
				</jstl:forEach>
				</div>
				<div class="column-50">
				<canvas id="max2-canvas"></canvas>
				</div>
			</table>
			
			<h3>
				<acme:message
					code="administrator.administrator-dashboard.form.label.average-budget-fine-dishes-by-status" />
			</h3>

			<table class="table table-sm">
			<caption></caption>
			<div class="column">
				<jstl:forEach
					items="${ averageBudgetFineDishesByStatus.keySet() }"
					var="key">
					<tr>
						<jstl:set
							value="${ averageBudgetFineDishesByStatus.get(key) }"
							var="amount" />
						<jstl:if test="${ amount>0 }">
							<th scope="row" style="width: 15%"><acme:message
									code="administrator.dashboard.form.status.${ key.getFirst() }" />
									<acme:message
									code="administrator.dashboard.form.status.${ key.getSecond() }" />
							</th>
							<td><acme:print value="${ amount }" /></td>
						</jstl:if>

					</tr>
				</jstl:forEach>
				</div>
				<div class="column-50">
				<canvas id="avg3-canvas"></canvas>
				</div>
			</table>
			<h3>
				<acme:message
					code="administrator.administrator-dashboard.form.label.deviation-budget-fine-dishes-by-status" />
			</h3>

			<table class="table table-sm">
			<caption></caption>
			<div class="column">
				<jstl:forEach
					items="${ deviationBudgetFineDishesByStatus.keySet() }"
					var="key">
					<tr>
						<jstl:set
							value="${ deviationBudgetFineDishesByStatus.get(key) }"
							var="amount" />
						<jstl:if test="${ amount>0 }">
							<th scope="row" style="width: 15%"><acme:message
									code="administrator.dashboard.form.status.${ key.getFirst() }" />
									<acme:message
									code="administrator.dashboard.form.status.${ key.getSecond() }" /></th>
							<td><acme:print value="${ amount }" /></td>
						</jstl:if>

					</tr>
				</jstl:forEach>
				</div>
				<div class="column-50">
				<canvas id="dev3-canvas"></canvas>
				</div>
			</table>

			<h3>
				<acme:message
					code="administrator.administrator-dashboard.form.label.minimum-budget-fine-dishes-by-status" />
			</h3>

			<table class="table table-sm">
			<caption></caption>
			<div class="column">
				<jstl:forEach items="${ minimumBudgetFineDishesByStatus.keySet() }"
					var="key">
					<tr>
						<jstl:set value="${ minimumBudgetFineDishesByStatus.get(key) }"
							var="amount" />
						<jstl:if test="${ amount>0 }">
							<th scope="row" style="width: 15%"><acme:message
									code="administrator.dashboard.form.status.${ key.getFirst() }" />
									<acme:message
									code="administrator.dashboard.form.status.${ key.getSecond() }" /></th>
							<td><acme:print value="${ amount }" /></td>
						</jstl:if>

					</tr>
				</jstl:forEach>
				</div>
				<div class="column-50">
				<canvas id="min3-canvas"></canvas>
				</div>
			</table>

			<h3>
				<acme:message
					code="administrator.administrator-dashboard.form.label.maximum-budget-fine-dishes-by-status" />
			</h3>
			<table class="table table-sm">
			<caption></caption>
			<div class="column">
				<jstl:forEach items="${ maximumBudgetFineDishesByStatus.keySet() }"
					var="key">
					<tr>
						<jstl:set value="${ maximumBudgetFineDishesByStatus.get(key) }"
							var="amount" />
						<jstl:if test="${ amount>0 }">
							<th scope="row" style="width: 15%"><acme:message
									code="administrator.dashboard.form.status.${ key.getFirst() }" />
									<acme:message
									code="administrator.dashboard.form.status.${ key.getSecond() }" /></th>
							<td><acme:print value="${ amount }" /></td>
						</jstl:if>

					</tr>
				</jstl:forEach>
				</div>
				<div class="column-50">
				<canvas id="max3-canvas"></canvas>
				</div>
			</table>
			<br> <br>
		</div>
</acme:form>

<script type="text/javascript">
   var totalNumberOfPFineDishesByStatus = {
   	<jstl:forEach items="${totalNumberOfPFineDishesByStatus}" var="item" varStatus="loop">
   	      ${item.key}: '${item.value}' ${not loop.last ? ',' : ''}
   	</jstl:forEach>
   };
   
   const total_data = {
     labels: Object.keys(totalNumberOfPFineDishesByStatus),
     datasets: [{
       data: Object.values(totalNumberOfPFineDishesByStatus),
       backgroundColor: [
    	   'rgb(254, 136, 127)',
     	  'rgb(136, 255, 114)',
     	  'rgb(71, 130, 255)'
       ],
       borderColor: [
    	   'rgb(193, 136, 127)',
     	  'rgb(136, 176, 114)',
     	  'rgb(71, 130, 159)'
       ],
       borderWidth: 1
     }]
   };
   
   var options = {
   	    legend : { display : false },
   	    scales: {
   	        y: {
   	         suggestedMin: 0,
   	         display: true
   	        }
   	      }
   	};
   
   var canvas = document.getElementById("total-canvas");
   var context = canvas.getContext("2d");
   new Chart(context, {
   	type : "bar",
   	data : total_data,
   	options: options
   });
</script>

<script type="text/javascript">
   var averageRetailPriceOfIngredientByCurrency = {
   	   	<jstl:forEach items="${averageRetailPriceOfIngredientByCurrency}" var="item" varStatus="loop">
   	   	      "${item.key}": '${item.value}' ${not loop.last ? ',' : ''}
   	   	</jstl:forEach>
   	   };
   
   const average_data = {
    labels: Object.keys(averageRetailPriceOfIngredientByCurrency),
    datasets: [{
      data: Object.values(averageRetailPriceOfIngredientByCurrency),
      backgroundColor: [
    	  'rgb(254, 136, 127)',
    	  'rgb(136, 255, 114)',
    	  'rgb(71, 130, 255)'
          
        ]
    }]
   };
   
   var canvas = document.getElementById("average-canvas");
   var context = canvas.getContext("2d");
   new Chart(context, {
   	type : "pie",
   	data : average_data,
   	options: options
   });
</script>

<script type="text/javascript">
   var deviationRetailPriceOfIngredientByCurrency = {
   	   	<jstl:forEach items="${deviationRetailPriceOfIngredientByCurrency}" var="item" varStatus="loop">
   	   	      "${item.key}": '${item.value}' ${not loop.last ? ',' : ''}
   	   	</jstl:forEach>
   	   };
   
   const dev_data = {
    labels: Object.keys(deviationRetailPriceOfIngredientByCurrency),
    datasets: [{
      data: Object.values(deviationRetailPriceOfIngredientByCurrency),
      backgroundColor: [
    	  'rgb(254, 136, 127)',
    	  'rgb(136, 255, 114)',
    	  'rgb(71, 130, 255)'
        ]
    }]
   };
   
   var canvas = document.getElementById("dev-canvas");
   var context = canvas.getContext("2d");
   new Chart(context, {
   	type : "pie",
   	data : dev_data,
   	options: options
   });
</script>

<script type="text/javascript">
   var minimumRetailPriceOfIngredientByCurrency = {
   	   	<jstl:forEach items="${minimumRetailPriceOfIngredientByCurrency}" var="item" varStatus="loop">
   	   	      "${item.key}": '${item.value}' ${not loop.last ? ',' : ''}
   	   	</jstl:forEach>
   	   };
   
   const min_data = {
    labels: Object.keys(minimumRetailPriceOfIngredientByCurrency),
    datasets: [{
      data: Object.values(minimumRetailPriceOfIngredientByCurrency),
      backgroundColor: [
    	  'rgb(254, 136, 127)',
    	  'rgb(136, 255, 114)',
    	  'rgb(71, 130, 255)'
        ]
    }]
   };
   
   var canvas = document.getElementById("min-canvas");
   var context = canvas.getContext("2d");
   new Chart(context, {
   	type : "pie",
   	data : min_data,
   	options: options
   });
</script>

<script type="text/javascript">
   var maximumRetailPriceOfIngredientByCurrency = {
   	   	<jstl:forEach items="${maximumRetailPriceOfIngredientByCurrency}" var="item" varStatus="loop">
   	   	      "${item.key}": '${item.value}' ${not loop.last ? ',' : ''}
   	   	</jstl:forEach>
   	   };
   
   const max_data = {
    labels: Object.keys(maximumRetailPriceOfIngredientByCurrency),
    datasets: [{
      data: Object.values(maximumRetailPriceOfIngredientByCurrency),
      backgroundColor: [
    	  'rgb(254, 136, 127)',
    	  'rgb(136, 255, 114)',
    	  'rgb(71, 130, 255)'
        ]
    }]
   };
   
   var canvas = document.getElementById("max-canvas");
   var context = canvas.getContext("2d");
   new Chart(context, {
   	type : "pie",
   	data : max_data,
   	options: options
   });
</script>

<script type="text/javascript">
   var averageRetailPriceOfKitchenUtensilsByCurrency = {
   	<jstl:forEach items="${averageRetailPriceOfKitchenUtensilsByCurrency}" var="item" varStatus="loop">
   	      ${item.key}: '${item.value}' ${not loop.last ? ',' : ''}
   	</jstl:forEach>
   };
   
   const avg2_data = {
     labels: Object.keys(averageRetailPriceOfKitchenUtensilsByCurrency),
     datasets: [{
       data: Object.values(averageRetailPriceOfKitchenUtensilsByCurrency),
       backgroundColor: [
    	   'rgb(254, 136, 127)',
     	  'rgb(136, 255, 114)',
     	  'rgb(71, 130, 255)'
       ],
       borderColor: [
    	   'rgb(193, 136, 127)',
     	  'rgb(136, 176, 114)',
     	  'rgb(71, 130, 159)'
       ],
       borderWidth: 1
     }]
   };
   
   var options = {
   	    legend : { display : false },
   	    scales: {
   	        y: {
   	         suggestedMin: 0,
   	         display: true
   	        }
   	      }
   	};
   
   var canvas = document.getElementById("avg2-canvas");
   var context = canvas.getContext("2d");
   new Chart(context, {
   	type : "bar",
   	data : avg2_data,
   	options: options
   });
</script>

<script type="text/javascript">
   var deviationRetailPriceOfKitchenUtensilsByCurrency = {
   	<jstl:forEach items="${deviationRetailPriceOfKitchenUtensilsByCurrency}" var="item" varStatus="loop">
   	      ${item.key}: '${item.value}' ${not loop.last ? ',' : ''}
   	</jstl:forEach>
   };
   
   const dev2_data = {
     labels: Object.keys(deviationRetailPriceOfKitchenUtensilsByCurrency),
     datasets: [{
       data: Object.values(deviationRetailPriceOfKitchenUtensilsByCurrency),
       backgroundColor: [
    	   'rgb(254, 136, 127)',
     	  'rgb(136, 255, 114)',
     	  'rgb(71, 130, 255)'
       ],
       borderColor: [
    	   'rgb(193, 136, 127)',
     	  'rgb(136, 176, 114)',
     	  'rgb(71, 130, 159)'
       ],
       borderWidth: 1
     }]
   };
   
   var options = {
   	    legend : { display : false },
   	    scales: {
   	        y: {
   	         suggestedMin: 0,
   	         display: true
   	        }
   	      }
   	};
   
   var canvas = document.getElementById("dev2-canvas");
   var context = canvas.getContext("2d");
   new Chart(context, {
   	type : "bar",
   	data : dev2_data,
   	options: options
   });
</script>

<script type="text/javascript">
   var minimumRetailPriceOfKitchenUtensilsByCurrency = {
   	<jstl:forEach items="${minimumRetailPriceOfKitchenUtensilsByCurrency}" var="item" varStatus="loop">
   	      ${item.key}: '${item.value}' ${not loop.last ? ',' : ''}
   	</jstl:forEach>
   };
   
   const min2_data = {
     labels: Object.keys(minimumRetailPriceOfKitchenUtensilsByCurrency),
     datasets: [{
       data: Object.values(minimumRetailPriceOfKitchenUtensilsByCurrency),
       backgroundColor: [
    	   'rgb(254, 136, 127)',
     	  'rgb(136, 255, 114)',
     	  'rgb(71, 130, 255)'
       ],
       borderColor: [
    	   'rgb(193, 136, 127)',
     	  'rgb(136, 176, 114)',
     	  'rgb(71, 130, 159)'
       ],
       borderWidth: 1
     }]
   };
   
   var options = {
   	    legend : { display : false },
   	    scales: {
   	        y: {
   	         suggestedMin: 0,
   	         display: true
   	        }
   	      }
   	};
   
   var canvas = document.getElementById("min2-canvas");
   var context = canvas.getContext("2d");
   new Chart(context, {
   	type : "bar",
   	data : min2_data,
   	options: options
   });
</script>

<script type="text/javascript">
   var maximumRetailPriceOfKitchenUtensilsByCurrency = {
   	<jstl:forEach items="${maximumRetailPriceOfKitchenUtensilsByCurrency}" var="item" varStatus="loop">
   	      ${item.key}: '${item.value}' ${not loop.last ? ',' : ''}
   	</jstl:forEach>
   };
   
   const max2_data = {
     labels: Object.keys(maximumRetailPriceOfKitchenUtensilsByCurrency),
     datasets: [{
       data: Object.values(maximumRetailPriceOfKitchenUtensilsByCurrency),
       backgroundColor: [
    	   'rgb(254, 136, 127)',
     	  'rgb(136, 255, 114)',
     	  'rgb(71, 130, 255)'
       ],
       borderColor: [
    	   'rgb(193, 136, 127)',
     	  'rgb(136, 176, 114)',
     	  'rgb(71, 130, 159)'
       ],
       borderWidth: 1
     }]
   };
   
   var options = {
   	    legend : { display : false },
   	    scales: {
   	        y: {
   	         suggestedMin: 0,
   	         display: true
   	        }
   	      }
   	};
   
   var canvas = document.getElementById("max2-canvas");
   var context = canvas.getContext("2d");
   new Chart(context, {
   	type : "bar",
   	data : max2_data,
   	options: options
   });
</script>

<script type="text/javascript">
   var averageBudgetFineDishesByStatus = {
   	   	<jstl:forEach items="${averageBudgetFineDishesByStatus}" var="item" varStatus="loop">
   	   	      "${item.key}": '${item.value}' ${not loop.last ? ',' : ''}
   	   	</jstl:forEach>
   	   };
   
   const avg3_data = {
    labels: Object.keys(averageBudgetFineDishesByStatus),
    datasets: [{
      data: Object.values(averageBudgetFineDishesByStatus),
      backgroundColor: [
    	  'rgb(254, 136, 127)',
    	  'rgb(136, 255, 114)',
    	  'rgb(71, 130, 255)'
        ]
    }]
   };
   
   var canvas = document.getElementById("avg3-canvas");
   var context = canvas.getContext("2d");
   new Chart(context, {
   	type : "pie",
   	data : avg3_data,
   	options: options
   });
</script>

<script type="text/javascript">
   var deviationBudgetFineDishesByStatus = {
   	   	<jstl:forEach items="${deviationBudgetFineDishesByStatus}" var="item" varStatus="loop">
   	   	      "${item.key}": '${item.value}' ${not loop.last ? ',' : ''}
   	   	</jstl:forEach>
   	   };
   
   const dev3_data = {
    labels: Object.keys(deviationBudgetFineDishesByStatus),
    datasets: [{
      data: Object.values(deviationBudgetFineDishesByStatus),
      backgroundColor: [
    	  'rgb(254, 136, 127)',
    	  'rgb(136, 255, 114)',
    	  'rgb(71, 130, 255)'
        ]
    }]
   };
   
   var canvas = document.getElementById("dev3-canvas");
   var context = canvas.getContext("2d");
   new Chart(context, {
   	type : "pie",
   	data : dev3_data,
   	options: options
   });
</script>

<script type="text/javascript">
   var minimumBudgetFineDishesByStatus = {
   	   	<jstl:forEach items="${minimumBudgetFineDishesByStatus}" var="item" varStatus="loop">
   	   	      "${item.key}": '${item.value}' ${not loop.last ? ',' : ''}
   	   	</jstl:forEach>
   	   };
   
   const min3_data = {
    labels: Object.keys(minimumBudgetFineDishesByStatus),
    datasets: [{
      data: Object.values(minimumBudgetFineDishesByStatus),
      backgroundColor: [
    	  'rgb(254, 136, 127)',
    	  'rgb(136, 255, 114)',
    	  'rgb(71, 130, 255)'
        ]
    }]
   };
   
   var canvas = document.getElementById("min3-canvas");
   var context = canvas.getContext("2d");
   new Chart(context, {
   	type : "pie",
   	data : min3_data,
   	options: options
   });
</script>

<script type="text/javascript">
   var maximumBudgetFineDishesByStatus = {
   	   	<jstl:forEach items="${maximumBudgetFineDishesByStatus}" var="item" varStatus="loop">
   	   	      "${item.key}": '${item.value}' ${not loop.last ? ',' : ''}
   	   	</jstl:forEach>
   	   };
   
   const max3_data = {
    labels: Object.keys(maximumBudgetFineDishesByStatus),
    datasets: [{
      data: Object.values(maximumBudgetFineDishesByStatus),
      backgroundColor: [
    	  'rgb(254, 136, 127)',
    	  'rgb(136, 255, 114)',
    	  'rgb(71, 130, 255)'
        ]
    }]
   };
   
   var canvas = document.getElementById("max3-canvas");
   var context = canvas.getContext("2d");
   new Chart(context, {
   	type : "pie",
   	data : max3_data,
   	options: options
   });
</script>