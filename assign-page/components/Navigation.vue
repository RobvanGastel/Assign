<template>
	<div class="row">

		<section class="brand-nav">
			<div class="container">
				<div class="row">
					<div class="col-xs-12 col-lg-10 col-lg-offset-1">

						<nuxt-link :to="'/'" class="logo" title="Ga naar home">
							<img class="logo-image" alt="Assign logo" src="/logo.png">
							<h4 class="logo-text">Assign</h4>
						</nuxt-link>

						<nav>
							<ul>
								<!-- Change on launch -->
								<!-- <li><nuxt-link class="lnk hidden-xs hidden-sm" title="Download" :to="'#appstore'">Download</nuxt-link></li> -->
								<li><nuxt-link class="lnk hidden-xs hidden-sm" title="Contact" :to="'/contact'">Contact</nuxt-link></li>
								<li><nuxt-link class="lnk hidden-xs hidden-sm" title="Voor Scholen" :to="'/voor-scholen'">Voor Scholen</nuxt-link></li>
								<li class="dropdown-toggle hidden-xs hidden-sm"><p class="lnk dropdown-toggle" title="Functies">Functies</p>
									<ul class="dropdown-nav">
										<li><nuxt-link class="lnk lnk-dropdown" title="Lees over Hashtags" :to="'/functies/hashtags'">Hashtags</nuxt-link></li>
										<li><nuxt-link class="lnk lnk-dropdown" title="Lees over Profielen" :to="'/functies/profiel'">Profielen</nuxt-link></li>
										<li><nuxt-link class="lnk lnk-dropdown" title="Lees over Notificaties" :to="'/functies/notificaties'">Notificaties</nuxt-link></li>
										<li><nuxt-link class="lnk lnk-dropdown" title="Lees over Communicatie manieren" :to="'/functies/communicatie'">Communicatie Manieren</nuxt-link></li>
										<li><nuxt-link class="lnk lnk-dropdown" title="Lees over het Delen van vragen" :to="'/functies/delen'">Vragen Delen</nuxt-link></li>
									</ul>
								</li>
								<li><button class="btn-navcta" title="Vraag een demo aan" @click="showModal = true">Vraag demo aan</button></li>
							</ul>
						</nav>

					</div>
				</div>
			</div>
		</section>

		<modal-license v-if="showModal" @close="showModal = false"></modal-license>

	</div>
</template>

<script>
import ModalLicense from './ModalLicense'
export default {
  components: {
    ModalLicense
  },
  data: () => ({
    showModal: false
  })
}
</script>
