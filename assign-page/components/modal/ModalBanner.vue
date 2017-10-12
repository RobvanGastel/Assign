<template>
	<div class="row component-modallicense">

		<section class="modal" id="modal-license">
			<div class="container">
				<div class="row">

					<div class="col-xs-12 col-sm-10 col-md-8 col-lg-6 col-sm-offset-1 col-md-offset-2 col-lg-offset-3">

						<div class="modal-license">
							<div class="modal-license-header">
								<img src="../../assets/img/close.svg" alt="Sluiten" id="close-modal-license" draggable="false" @click="$emit('close')">
								<h5>Beta licentie aanvragen</h5>
								<h2>Vul hier de gegevens in om een licentie aan te vragen.</h2>
							</div>
						</div>
          			</div>

        		</div>
      		</div>
    	</section>

  	</div>
</template>


<script>
export default {
  data: () => ({
    showModal: false
  })
}
</script>
