package com.springframework.thepinkfloydsound.batch;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.springframework.thepinkfloydsound.entity.Person;
import com.springframework.thepinkfloydsound.processor.PersonItemProcessor;

@Configuration
@EnableBatchProcessing
public class PersonBatchConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	public DataSource dataSource;
	
	@Bean
	public FlatFileItemReader<Person> personReaderCSV(){
		return new FlatFileItemReaderBuilder<Person>()
				.name("personReaderCSV")
				.resource(new ClassPathResource("person.csv"))
				.delimited()
				.names(new String[] {"firstName", "lastName", "age"})
				.linesToSkip(1)
				.fieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {
					{
						setTargetType(Person.class);

					}
				}).build();
	}
	
	@Bean
	public PersonItemProcessor personProcessor() {
		return new PersonItemProcessor();
	}
	
	@Bean
	public JdbcBatchItemWriter<Person> writerPerson(DataSource dataSource){
		return new JdbcBatchItemWriterBuilder<Person>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Person>())
				.sql("INSERT INTO person (first_name, last_name, age) VALUES (:firstName, :lastName, :age)")
				.dataSource(dataSource)
				.build();		
	}
	
	@Bean
	public Step stepPerson(JdbcBatchItemWriter<Person> writerPerson) {
		return stepBuilderFactory
				.get("stepPerson")
				.<Person,Person>chunk(10)
				.reader(personReaderCSV())
				.processor(personProcessor())
				.writer(writerPerson)
				.build();
	}
		
	@Bean
	public Job importPerson(Step stepPerson) {
		return jobBuilderFactory.get("importPerson")
				.incrementer(new RunIdIncrementer())
				.flow(stepPerson)
				.end()
				.build();
	}
}
