<template>
	<div class="row component-modallicense">

		<section class="modal" id="modal-license">
			<div class="container">
				<div class="row">

					<div class="col-xs-12 col-sm-10 col-md-8 col-lg-6 col-sm-offset-1 col-md-offset-2 col-lg-offset-3">

						<div class="modal-license">
							<div class="modal-license-header">
								<img src="../assets/img/close.svg" alt="Sluiten" id="close-modal-license" draggable="false" @click="$emit('close')">
								<h5>Beta licentie aanvragen</h5>
								<h2>Vul hier de gegevens in om een licentie aan te vragen.</h2>
							</div>

							<div class="modal-license-content">
								<p>Wij zullen een mailtje sturen met de benodigde informatie om de app te gaan gebruiken in jullie school omgeving.</p>

								<!-- Begin MailChimp Signup Form -->
								<form action="https://assignapp.us16.list-manage.com/subscribe/post?u=06ec3ad60c8473d5c82f6f977&amp;id=1ba02f2337" method="post" id="mc-embedded-subscribe-form" name="mc-embedded-subscribe-form" class="validate" target="_blank" novalidate>
									<label for="mce-FNAME">Voornaam</label>
									<input type="text" name="FNAME" id="mce-FNAME" placeholder="Vul hier uw voornaam in" class="modal-inputtext"/>

									<label for="mce-LNAME">Achternaam</label>
									<input type="text" name="LNAME" id="mce-LNAME" placeholder="Vul hier uw achternaam in" class="modal-inputtext"/>

									<label for="mce-EMAIL">E-mail</label>
									<input type="email" name="EMAIL" id="mce-EMAIL" placeholder="Vul hier uw e-mailadres in" class="modal-inputtext"/>

									<label for="mce-MMERGE4">schoolnaam</label>
									<input type="text" name="MMERGE4" id="mce-MMERGE4" placeholder="Vul hier de naam van uw school in" class="modal-inputtext"/>

									<label>Aantal studenten</label>
									<div class="modal-radiobox">
										<input type="radio" name="MMERGE3" id="mce-MMERGE3-0" value="Tot 500">
										<label for="mce-MMERGE3-0">
											<img src="../assets/img/checkbox.svg" alt="Ik accepteer de Gebruiksvoorwaarden" class="icon-checkbox">
											<img src="../assets/img/checkedbox.svg" alt="Ik accepteer de Gebruiksvoorwaarden" class="icon-checkedbox">
											Tot 500
										</label>
									</div>

									<div class="modal-radiobox modal-radiobox-center">
										<input type="radio" name="MMERGE3" id="mce-MMERGE3-1" value="500 - 1500">
										<label for="mce-MMERGE3-1">
											<img src="../assets/img/checkbox.svg" alt="Ik accepteer de Gebruiksvoorwaarden" class="icon-checkbox">
											<img src="../assets/img/checkedbox.svg" alt="Ik accepteer de Gebruiksvoorwaarden" class="icon-checkedbox">
											500 - 1500
										</label>
									</div>

									<div class="modal-radiobox">
										<input type="radio" name="MMERGE3" id="mce-MMERGE3-2" value="1500+">
										<label for="mce-MMERGE3-2">
											<img src="../assets/img/checkbox.svg" alt="Ik accepteer de Gebruiksvoorwaarden" class="icon-checkbox">
											<img src="../assets/img/checkedbox.svg" alt="Ik accepteer de Gebruiksvoorwaarden" class="icon-checkedbox">
											1500+
										</label>
									</div>

									<div id="mce-responses" class="clear">
										<div class="response" id="mce-error-response" style="display:none"></div>
										<div class="response" id="mce-success-response" style="display:none"></div>
									</div><!-- real people should not fill this in and expect good things - do not remove this or risk form bot signups-->
									<div style="position: absolute; left: -5000px;" aria-hidden="true"><input type="text" name="b_06ec3ad60c8473d5c82f6f977_1ba02f2337" tabindex="-1" value=""></div>
									<div class="clear">
										<input type="submit" value="Vraag een licentie aan" name="subscribe" id="mc-embedded-subscribe" class="btn btn-primary"/>
									</div>

									<div class="mc-field-group input-group">
										<label class="modal-checkbox" for="mce-MMERGE5-0">
											<input type="radio" value="Ik ga akkoord met de Gebruiksvoorwaarden" name="MMERGE5" id="mce-MMERGE5-0"/>
											<img src="../assets/img/checkedbox.svg" alt="Ik accepteer de Gebruiksvoorwaarden" class="icon-checkedbox">
											<img src="../assets/img/checkbox.svg" alt="Ik accepteer de Gebruiksvoorwaarden" class="icon-checkbox">
										</label>
										<p class="voorwaarden-text">Ik heb de <nuxt-link title="Bekijk de Gebruiksvoorwaarden" :to="'/gebruiksvoorwaarden'">Gebruiksvoorwaarden</nuxt-link> gelezen</p>
									</div>

								</form>
								<!-- End mc_embed_signup -->

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
