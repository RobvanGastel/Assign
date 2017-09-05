<template>
	<div class="row">
		<section class="brand-nav">
			<div class="container">
				<div class="row">
					<div class="col-xs-12 col-md-10 col-md-offset-1">

						<nuxt-link :to="'/'">
							<img class="logo" alt="Assign logo" src="/logo.png">
							<h4 class="logo-text">Assign</h4>
						</nuxt-link>
						<div class="headway"></div>

						<nav>
							<ul>
								<li><nuxt-link class="lnk" alt="Download" :to="'#appstore'">Download</nuxt-link></li>
								<li><nuxt-link class="lnk" alt="Contact" :to="'/contact'">Contact</nuxt-link></li>
								<li><nuxt-link class="lnk" alt="Vragen" :to="'/faq'">Vragen</nuxt-link></li>
								<li class="dropdown-toggle"><nuxt-link class="lnk dropdown-toggle" alt="Functies" :to="'/functies'">Functies</nuxt-link>
									<ul class="dropdown-menu">
										<li><nuxt-link class="lnk lnk-dropdown" alt="Functie1" :to="'/functies#functie1'">Functie1</nuxt-link></li>
										<li><nuxt-link class="lnk lnk-dropdown" alt="Functie2" :to="'/functies#functie2'">Functie2</nuxt-link></li>
										<li><nuxt-link class="lnk lnk-dropdown" alt="Functie3" :to="'/functies#functie3'">Functie3</nuxt-link></li>
									</ul>
								</li>
								<li><nuxt-link class="btn-navcta" alt="Vraag een demo aan" :to="'#demo'">Vraag demo aan</nuxt-link></li>
							</ul>
						</nav>

					</div>
				</div>
			</div>
		</section>
	</div>
</template>
