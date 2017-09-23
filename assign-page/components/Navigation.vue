<template>
	<div class="row">
		<section class="brand-nav">
			<div class="container">
				<div class="row">
					<div class="col-xs-12 col-md-10 col-md-offset-1">

						<nuxt-link :to="'/'" class="logo" title="Ga naar home">
							<img class="logo-image" alt="Assign logo" src="/logo.png">
							<h4 class="logo-text">Assign</h4>
						</nuxt-link>
						<div class="headway"></div>

						<nav>
							<ul>
								<li><nuxt-link class="lnk" title="Download" :to="'#appstore'">Download</nuxt-link></li>
								<li><nuxt-link class="lnk" title="Contact" :to="'/contact'">Contact</nuxt-link></li>
								<li><nuxt-link class="lnk" title="Vragen" :to="'/faq'">Vragen</nuxt-link></li>
								<li class="dropdown-toggle"><nuxt-link class="lnk dropdown-toggle" title="Functies" :to="'/functies'">Functies</nuxt-link>
									<ul class="dropdown-nav">
										<li><nuxt-link class="lnk lnk-dropdown" title="Lees over Hashtags" :to="'/functies/hashtags'">Hashtags</nuxt-link></li>
										<li><nuxt-link class="lnk lnk-dropdown" title="Lees over Profielen" :to="'/functies/profiel'">Profielen</nuxt-link></li>
										<li><nuxt-link class="lnk lnk-dropdown" title="Lees over Notificaties" :to="'/functies/notificaties'">Notificaties</nuxt-link></li>
									</ul>
								</li>
								<li><nuxt-link class="btn-navcta" title="Vraag een demo aan" :to="'#demo'">Vraag demo aan</nuxt-link></li>
							</ul>
						</nav>

					</div>
				</div>
			</div>
		</section>
	</div>
</template>
