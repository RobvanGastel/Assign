<template>
	<div class="row">
		<section class="brand-nav">
			<div class="container">
				<div class="row">
					<div class="col-xs-12 col-md-10 col-md-offset-1">
						<nuxt-link :to="'/'" class="logo" title="Ga naar home">
							<img class="logo-image" src="/logo.png" alt="Assign logo">
							<h4 class="logo-text">Assign</h4>
						</nuxt-link>
						<nav>
							<ul>
								<li><nuxt-link class="btn-navcta" title="Home" :to="'/'">Bekijk website</nuxt-link></li>
							</ul>
						</nav>
					</div>
				</div>
			</div>
		</section>
	</div>
</template>
