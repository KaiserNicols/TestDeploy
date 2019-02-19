<<<<<<< HEAD
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewRecComponent } from './new-rec.component';

describe('NewRecComponent', () => {
  let component: NewRecComponent;
  let fixture: ComponentFixture<NewRecComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewRecComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewRecComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
=======
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewRecComponent } from './new-rec.component';

describe('NewRecComponent', () => {
  let component: NewRecComponent;
  let fixture: ComponentFixture<NewRecComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewRecComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewRecComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
>>>>>>> 376ebaa769da514437fdbd9b194ccee239788514
